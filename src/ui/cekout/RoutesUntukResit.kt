package com.belajar.ui.cekout

import com.belajar.DataManagerMongo
import com.belajar.ui.Endpoints
import com.belajar.ui.login.Sesi
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Route.routesUntukResit() {
    get(Endpoints.RECEIPT.url) {
        val sesi = call.sessions.get<Sesi>()
        call.respondHtmlTemplate(
            ResitTemplet(sesi, DataManagerMongo.INSTANCE.troliPembeli(sesi))
        ) {}
    }
}