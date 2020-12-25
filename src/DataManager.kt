package com.belajar

class DataManager {
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

    fun semuaBuku() = bukuan
}