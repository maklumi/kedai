package com.belajar.ui.login

import com.belajar.ui.Endpoints
import com.belajar.ui.GeneralViewTemplate
import io.ktor.html.*
import kotlinx.html.*

class LoginTemplate(sesi: Sesi?) : Template<HTML> {
    val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(sesi)
    val greeting = Placeholder<FlowContent>()

    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div("mt-2") {
                    h2() {
                        +"Welcome to the Kedai"
                    }
                    p {
                        insert(greeting)
                    }
                }
                form(
                    method = FormMethod.post,
                    encType = FormEncType.multipartFormData,
                    action = Endpoints.DOLOGIN.url
                ) {
                    div("mb-3") {
                        input(type = InputType.text, classes = "form-control", name = "username") {
                            placeholder = "Masukkan nama"
                            attributes["aria-label"] = "Username"
                            attributes["aria-describedby"] = "basic-addon1"
                        }
                    }
                    div("mb-3") {
                        input(type = InputType.text, classes = "form-control", name = "password") {
                            placeholder = "Masukkan katalaluan"
                            attributes["aria-label"] = "Password"
                            attributes["aria-describedby"] = "basic-addon1"
                        }
                    }
                    div("mb-3") {
                        button(classes = "btn btn-primary", type = ButtonType.submit) {
                            +"Login"
                        }
                    }
                }
            }
        }
    }
}