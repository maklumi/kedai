package com.belajar.ui.login

import com.belajar.JagaSekuriti
import com.belajar.Konstant
import com.belajar.ui.Endpoints
import com.belajar.ui.home.HomeTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.sessions.*
import org.slf4j.LoggerFactory

data class Sesi(val namapengguna: String)

fun Route.loginView() {
    get(Endpoints.LOGIN.url) {
        call.respondHtmlTemplate(LoginTemplate(call.sessions.get<Sesi>())) {
        }
    }
    get(Endpoints.HOME.url) {
        call.respondHtmlTemplate(HomeTemplate(call.sessions.get<Sesi>())) {

        }
    }
    get(Endpoints.LOGOUT.url) {
        call.sessions.clear(Konstant.NAMA_COOKIE.value)
        call.respondHtmlTemplate(LogoutTemplate(call.sessions.get<Sesi>())) {

        }
    }

    post(Endpoints.DOLOGIN.url) {
        val log = LoggerFactory.getLogger("LoginView")
        val multipart = call.receiveMultipart()

        call.request.headers.forEach { s, list ->
            log.info("Kunci $s nilai $list")
        }
        var namapengguna = ""
        var katalaluan = ""
        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} - ${part.value}")
                    if (part.name == "username")
                        namapengguna = part.value
                    if (part.name == "password")
                        katalaluan = part.value
                }
                is PartData.FileItem -> {
                    log.info("FileItem: ${part.name} - ${part.originalFileName} of ${part.contentType}")
                }
                is PartData.BinaryItem -> {
                }
            }
            part.dispose()
        }
        if (JagaSekuriti().isValid(namapengguna, katalaluan)) {
            call.sessions.set(Konstant.NAMA_COOKIE.value, Sesi(namapengguna))
            call.respondHtmlTemplate(LoginSuccessTemplate(call.sessions.get<Sesi>())) {
                greeting {
                    +"Anda berjaya login sebagai $namapengguna"
                }
            }
        } else
            call.respondHtmlTemplate(LoginTemplate(call.sessions.get<Sesi>())) {
                greeting {
                    +"nama dan password salah"
                }
            }
    }
}





