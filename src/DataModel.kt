package com.belajar

data class Buku(var id: String, var tajuk: String, var harga: Float)
data class Troli(var id: String, var idPembeli: String, val barangan: ArrayList<Belian>)
data class Belian(var idBuku: String, var kuantiti: Int)
data class Pembeli(var id: String, var nama: String, var katalaluan: String)