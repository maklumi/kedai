package com.belajar

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@Location("/buku/list")
data class SenaraiBukuLoc(val sisih: String, val menaik: Boolean)

fun Route.bukuan() {
    val dataManager = DataManager()

    authenticate("jagaKedaiBuku") {
        get<SenaraiBukuLoc>() {
            call.respond(dataManager.susunan(it.sisih, it.menaik))
        }
    }

    route("/buku") {
        get("/") {
            call.respond(dataManager.semuaBuku())
        }

        post("") {
            val buku = call.receive(Buku::class)
            val kemasBuku = dataManager.kemaskiniBuku(buku)
            kemasBuku?.let { call.respond(kemasBuku) }
        }

        put("") {
            val buku = call.receive(Buku::class)
            val bukuBaru = dataManager.tambahBuku(buku)
            call.respond(bukuBaru)
        }

        delete("/{id}") {
            val id = call.parameters["id"].toString()
            val bukuDipadam = dataManager.padamBuku(id)
            if (bukuDipadam == null) call.respondText { "Tiada buku dengan id $id." }
            bukuDipadam?.let { call.respond(bukuDipadam) }
        }
    }
}