package com.belajar.ui

import com.belajar.DataManagerMongo
import com.belajar.ui.login.Sesi
import io.ktor.html.*
import kotlinx.html.*

class NavigationTemplate(val sesi: Sesi?) : Template<FlowContent> {
    val menuitems = PlaceholderList<UL, FlowContent>()

    override fun FlowContent.apply() {
        div {
            nav("navbar navbar-expand-md navbar-dark bg-dark") {
                a(classes = "navbar-brand", href = "#") { +"Kedai Ku" }
                button(classes = "navbar-toggler", type = ButtonType.button) {
                    attributes["data-toggle"] = "collapse"
                    attributes["data-target"] = "#navbarsExampleDefault"
                    attributes["aria-controls"] = "navbarExampleDefault"
                    attributes["aria-expanded"] = "false"
                    attributes["aria-label"] = "Toggle navigation"
                    span("navbar-toggler-icon") {}
                }
                div("collapse navbar-collapse") {
                    this.id = "navbarsExampleDefault"
                    ul("navbar-nav mr-auto") {
                        each(menuitems) {
                            li {
                                insert(it)
                            }
                        }
                    }
                }
                div(classes = "") {
                    if (sesi != null) {
                        val troli = DataManagerMongo.INSTANCE.troliPembeli(sesi)
                        form(action = Endpoints.CART.url) {
                            button(classes = "btn btn-danger", type = ButtonType.submit) {
                                +"Barangan dalam troli: ${troli.kuantitiTotal}, Harga semua: ${troli.jumlah}"
                            }
                        }
                    }
                }
            }
        }
    }
}