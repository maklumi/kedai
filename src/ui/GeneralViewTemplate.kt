package com.belajar.ui

import com.belajar.ui.login.Sesi
import io.ktor.html.*
import kotlinx.html.*

class GeneralViewTemplate(private val sesi: Sesi?) : Template<HTML> {
    val content = Placeholder<HtmlBlockTag>()

    override fun HTML.apply() {
        head {
            link(
                rel = "stylesheet",
                href = "https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css",
                type = "text/css"
            ) {
                integrity = "sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
                this.attributes["crossorigin"] = "anonymous"
            }
            title("Kedai Buku")
        }
        body {
            insert(NavigationTemplate(sesi)) {
                menuitems {
                    a(classes = "nav-link", href = Endpoints.HOME.url) {
                        +"Home"
                    }
                }
                if (sesi == null) {

                    menuitems {
                        a(classes = "nav-link", href = Endpoints.LOGIN.url) {
                            +"Sign in"
                        }
                    }
                } else {
                    menuitems {
                        a(classes = "nav-link", href = Endpoints.LOGOUT.url) {
                            +"Sign out"
                        }
                    }
                    menuitems {
                        a(classes = "nav-link", href = Endpoints.BOOKS.url) {
                            +"Books"
                        }
                    }
                }
            }

            div("container") {
                div("row") {
                    div("col-md-12") {
                        insert(content)
                    }
                }
            }
            script(
                type = "javascript",
                src = "https://code.jquery.com/jquery-3.5.1.slim.min.js"
            ) {
                integrity = "sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                attributes["crossorigin"] = "anonymous"
            }
            script(
                type = "javascript",
                src = "https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            ) {
                integrity = "sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
                attributes["crossorigin"] = "anonymous"
            }
        }
    }
}