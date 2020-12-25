package com.belajar

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.bukuan() {
    val dataManager = DataManager()
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
            bukuDipadam?.let { call.respond(bukuDipadam) }
        }
    }
}