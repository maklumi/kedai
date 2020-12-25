package com.belajar

import org.slf4j.LoggerFactory
import kotlin.reflect.full.declaredMemberProperties

@Deprecated("guna yang pakai mongo")
class DataManager {
    data class Buku(var id: String, var tajuk: String, var harga: Float)

    private val log = LoggerFactory.getLogger(DataManager::class.java)

    private var bukuan = ArrayList<Buku>()

    private fun kiraId() = bukuan.size.toString()

    init {
        bukuan.add(Buku(kiraId(), "Cara tanam pisang", 100.0f))
        bukuan.add(Buku(kiraId(), "Cara tanam betik", 90.0f))
        bukuan.add(Buku(kiraId(), "Cara tanam nanas", 80.0f))
        bukuan.add(Buku(kiraId(), "Cara tanam kelapa", 70.0f))
        bukuan.add(Buku(kiraId(), "Cara tanam pelam", 80.0f))
        bukuan.add(Buku(kiraId(), "Cara tanam oren", 60.0f))
    }

    fun tambahBuku(buku: Buku): Buku {
        bukuan.add(buku)
        return buku
    }

    fun kemaskiniBuku(buku: Buku): Buku? {
        val baru = bukuan.find {
            it.id == buku.id
        }
        baru?.tajuk = buku.tajuk
        baru?.harga = buku.harga
        return baru
    }

    fun padamBuku(buku: Buku): Buku? {
        val bukuDipadam = bukuan.find {
            it.id == buku.id
        }
        bukuan.remove(bukuDipadam)
        return bukuDipadam
    }

    fun padamBuku(idBuku: String): Buku? {
        val bukuDipadam = bukuan.find {
            it.id == idBuku
        }
        bukuan.remove(bukuDipadam)
        return bukuDipadam
    }

    private fun semuaBuku() = bukuan

    fun susunan(sisih: String, menaik: Boolean): Any {
        val member = Buku::class.declaredMemberProperties.find { it.name == sisih }
        if (member == null) {
            log.info("Field untuk sisihan tak ada.")
            return semuaBuku()
        }

        return if (menaik) {
            semuaBuku().sortedBy { member.get(it).toString() }
        } else {
            semuaBuku().sortedByDescending { member.get(it).toString() }
        }
    }
}