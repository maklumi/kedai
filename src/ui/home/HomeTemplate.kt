package com.belajar.ui.home

import com.belajar.ui.GeneralViewTemplate
import com.belajar.ui.login.Sesi
import io.ktor.html.*
import kotlinx.html.HTML
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.p

class HomeTemplate(sesi: Sesi?) : Template<HTML> {
    private val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(sesi)

    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div("mt-2") {
                    h2() {
                        +"Welcome to the Kedai"
                    }
                    p {
                        +"Ada banyak buku-buky yang bagus"
                    }
                }
            }
        }


    }
}