package com.belajar.ui.books

import com.belajar.Buku
import com.belajar.ui.Endpoints
import com.belajar.ui.GeneralViewTemplate
import com.belajar.ui.login.Sesi
import io.ktor.html.*
import kotlinx.html.*

class TempletBuku(sesi: Sesi?, private val books: List<Buku>) : Template<HTML> {
    private val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(sesi)
    val saringanPerkataan = Placeholder<FlowContent>()

    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div(classes = "mt-2") {
                    h2() {
                        +"Buku-buku yang ada"
                    }
                    div {
                        insert(saringanPerkataan)
                    }
                    form(
                        method = FormMethod.post,
                        encType = FormEncType.multipartFormData,
                        action = Endpoints.DOBOOKSEARCH.url
                    ) {
                        div(classes = "row mb-3 mt-3") {
                            div(classes = "md-6") {
                                input(type = InputType.text, classes = "form-control", name = "search") {
                                    this.placeholder = "Cari buku"
                                    this.attributes["aria-label"] = "Search"
                                    this.attributes["aria-describedby"] = "basic-addon1"
                                }
                            }
                            div(classes = "md-5 offset-md-1") {

                                button(classes = "btn btn-primary", type = ButtonType.submit) {
                                    +"Mula cari"
                                }


                            }
                        }
                    }

                    table(classes = "table table-striped") {
                        thead {
                            tr {
                                th(scope = ThScope.col) { +"Id" }
                                th(scope = ThScope.col) { +"Tajuk" }
                                th(scope = ThScope.col) { +"Harga" }
                                th(scope = ThScope.col) { +"" }
                            }
                        }
                        tbody {
                            books.forEach {
                                tr {
                                    td { +"${it.id}" }
                                    td { +it.tajuk }
                                    td { +"${it.harga}" }
                                    td {
                                        form(
                                            method = FormMethod.post,
                                            encType = FormEncType.multipartFormData,
                                            action = Endpoints.DOADDTOCART.url
                                        ) {
                                            button(classes = "btn btn-success", type = ButtonType.submit) {
                                                +"Masuk troli"
                                            }
                                            input(type = InputType.hidden, name = "bookid") {
                                                value = "${it.id}"
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}