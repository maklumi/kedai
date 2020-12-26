package com.belajar.ui.troli

import com.belajar.DataManagerMongo
import com.belajar.ui.Endpoints
import com.belajar.ui.books.TempletBuku
import com.belajar.ui.login.Sesi
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.sessions.*
import kotlinx.html.i
import org.slf4j.LoggerFactory


fun Route.routesUntukTroli() {
    get(Endpoints.CART.url) {
        val sesi = call.sessions.get<Sesi>()
        call.respondHtmlTemplate(
            CartTemplate(
                sesi, DataManagerMongo.INSTANCE.troliPembeli(sesi)
            )
        ) {}
    }
    post(Endpoints.DOADDTOCART.url) {
        val log = LoggerFactory.getLogger("LoginView")
        val multipart = call.receiveMultipart()
        val session = call.sessions.get<Sesi>()
        var bookid = ""
        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "bookid")
                        bookid = part.value
                }
                else -> {
                }
            }
            part.dispose()
        }
        val book = DataManagerMongo.INSTANCE.getBookWithId(bookid)
        book?.let { DataManagerMongo.INSTANCE.masukkanBukuDalamTroli(session, book) }
        call.respondHtmlTemplate(TempletBuku(call.sessions.get<Sesi>(), DataManagerMongo.INSTANCE.semuaBuku())) {
            saringanPerkataan {
                i {
                    +"Buku dimasukkan dalam troli"
                }
            }
        }
    }

    post(Endpoints.DOREMOVEFROMCART.url) {
        val log = LoggerFactory.getLogger("Remove from cart")
        val multipart = call.receiveMultipart()
        val session = call.sessions.get<Sesi>()
        var bookid = ""
        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "bookid")
                        bookid = part.value
                }
                else -> {
                }
            }
            part.dispose()
        }
        val book = DataManagerMongo.INSTANCE.getBookWithId(bookid)
        book?.let { DataManagerMongo.INSTANCE.removeBook(session, book) }
        call.respondHtmlTemplate(CartTemplate(session, DataManagerMongo.INSTANCE.troliPembeli(session))) {
        }
    }

}