package com.belajar.ui.books

import com.belajar.DataManagerMongo
import com.belajar.ui.Endpoints
import com.belajar.ui.login.Sesi
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.sessions.*
import kotlinx.html.i
import org.slf4j.LoggerFactory

fun Route.bukubuku() {
    get(Endpoints.BOOKS.url) {
        call.respondHtmlTemplate(
            TempletBuku(
                call.sessions.get<Sesi>(),
                DataManagerMongo.INSTANCE.semuaBuku()
            )
        ) {
        }
    }
    post(Endpoints.DOBOOKSEARCH.url) {
        val log = LoggerFactory.getLogger("LoginView")
        val multipart = call.receiveMultipart()
        var carian = ""
        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "search")
                        carian = part.value
                }
                else -> {
                }
            }
            part.dispose()
        }
        val bukucarian = DataManagerMongo.INSTANCE.cariBukubuku(carian)
        call.respondHtmlTemplate(TempletBuku(call.sessions.get<Sesi>(), bukucarian)) {
            saringanPerkataan {
                i {
                    +"Saring untuk perkataan: $carian"
                }
            }
        }
    }

}