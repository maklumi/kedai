package com.belajar.ui.cekout

import com.belajar.model.Troli
import com.belajar.ui.Endpoints
import com.belajar.ui.GeneralViewTemplate
import com.belajar.ui.login.Sesi
import io.ktor.html.*
import kotlinx.html.*

class ResitTemplet(val sesi: Sesi?, private val troli: Troli) : Template<HTML> {
    private val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(sesi)
    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div(classes = "mt-2 row") {
                    div(classes = "card") {
                        this.style {
                            +"width: 18rem;"
                        }
                        div(classes = "card-body") {
                            h5(classes = "card-title") {
                                +"Terima kasih kerana berbelanja di sini!"
                            }
                            p(classes = "card-text") {
                                +"""Ini senarai barang yand dibeli berjumlah ${troli.kuantitiTotal} buku, bernilai ${troli.jumlah} ringgit.  
                                    Tajuk buku yang dibeli ialah :"""
                                ul {
                                    troli.entries.forEach {
                                        li {
                                            +"${it.buku.tajuk} berharga ${it.buku.harga}"
                                        }
                                    }
                                }
                            }
                            p(classes = "card-text") {
                                +"Datang lagi untuk beli buku lain."
                            }
                            a(href = Endpoints.BOOKS.url, classes = "btn btn-primary") {
                                +"Beli lagi"
                            }
                        }
                    }
                }
            }
        }
    }
}