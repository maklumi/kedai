package com.belajar.ui.login

import com.belajar.ui.GeneralViewTemplate
import io.ktor.html.*
import kotlinx.html.HTML
import kotlinx.html.div

class LogoutTemplate(sesi: Sesi?) : Template<HTML> {
    val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(sesi)

    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div("mt-2") {
                    +"Anda dah keluar"
                }
            }
        }
    }
}