package com.belajar.ui.troli

import com.belajar.model.Troli
import com.belajar.ui.Endpoints
import com.belajar.ui.GeneralViewTemplate
import com.belajar.ui.login.Sesi
import io.ktor.html.*
import kotlinx.html.*


class CartTemplate(val sesi: Sesi?, private val troli: Troli) : Template<HTML> {
    private val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(sesi)
    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div(classes = "mt-2 row") {
                    h2() {
                        +"Troli barang belian"
                    }


                    table(classes = "table table-striped") {
                        thead {
                            tr {
                                th(scope = ThScope.col) { +"Tajuk" }
                                th(scope = ThScope.col) { +"Harga" }
                                th(scope = ThScope.col) { +"Kuantiti" }
                                th(scope = ThScope.col) { +"Jumlah" }
                                th(scope = ThScope.col) { +"" }
                            }
                        }
                        tbody {
                            troli.entries.forEach() {
                                tr {
                                    td { +it.buku.tajuk }
                                    td { +"${it.buku.harga}" }
                                    td { +"${it.kuantiti}" }
                                    td { +"${it.jumlah}" }
                                    td {
                                        form(
                                            method = FormMethod.post,
                                            encType = FormEncType.multipartFormData,
                                            action = Endpoints.DOREMOVEFROMCART.url
                                        ) {
                                            button(classes = "btn btn-success", type = ButtonType.submit) {
                                                +"Keluarkan 1 dari troli"
                                            }
                                            input(type = InputType.hidden, name = "bookid") {
                                                this.value = "${it.buku.id}"
                                            }
                                        }
                                    }
                                }
                            }
                            tr {

                            }
                            tr {
                                td {
                                    +"Jumlah semua"
                                }
                                td {
                                }
                                td {
                                }
                                td {
                                    +troli.kuantitiTotal.toString()
                                }
                                td {
                                    +troli.jumlah.toString()
                                }
                            }
                        }
                    }

                }
                div(classes = "row mt-3") {
                    form(
                        method = FormMethod.get,
                        encType = FormEncType.multipartFormData,
                        action = Endpoints.RECEIPT.url
                    ) {
                        button(classes = "btn btn-warning", type = ButtonType.submit) {
                            +"bayar dan resit"
                        }
                    }
                }
            }
        }
    }
}