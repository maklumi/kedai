package com.belajar

import com.belajar.ui.books.bukubuku
import com.belajar.ui.login.Sesi
import com.belajar.ui.login.loginView
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import org.slf4j.event.Level
import kotlin.collections.set

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
    install(Sessions) {
        cookie<Sesi>(Konstant.NAMA_COOKIE.value) {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(PartialContent) {
        // Maximum number of ranges that will be accepted from a HTTP request.
        // If the HTTP request specifies more ranges, they will all be merged into a single range.
        maxRangeCount = 10
    }

    val penggunaList = listOf("user1", "user2")
    val admins = listOf("admin1", "admin2")

    install(Authentication) {
        basic("jagaKedaiBuku") {
            realm = "Kedai Buku"
            validate {
                if ((penggunaList.contains(it.name) || admins.contains(it.name)) &&
                    it.password == "password"
                )
                    UserIdPrincipal(it.name)
                else null
            }
        }
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(Locations) {
    }

    routing {
        bukubuku()
        bukuan()
        loginView()

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        install(StatusPages) {
            exception<AuthenticationException> {
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> {
                call.respond(HttpStatusCode.Forbidden)
            }

        }

        authenticate("jagaKedaiBuku") {
            get("/api/cubaauth") {
                val principal = call.principal<UserIdPrincipal>()!!
                call.respondText("Hello ${principal.name}")
            }
        }

    }
}


class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

