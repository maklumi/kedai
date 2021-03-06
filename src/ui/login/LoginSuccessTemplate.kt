package com.belajar.ui.login

import com.belajar.ui.GeneralViewTemplate
import io.ktor.html.*
import kotlinx.html.FlowContent
import kotlinx.html.HTML
import kotlinx.html.div
import kotlinx.html.p

class LoginSuccessTemplate(sesi: Sesi?) : Template<HTML> {
    val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(sesi)
    val greeting = Placeholder<FlowContent>()

    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div("mt-2") {
                    +"Anda dah login"
                }
                p {
                    insert(greeting)
                }
            }
        }
    }
}