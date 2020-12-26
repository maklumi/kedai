package com.belajar

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

//data class Buku(var id: String, var tajuk: String, var harga: Float)
//data class Troli(var id: String, var idPembeli: String, val barangan: ArrayList<Belian>)
data class Belian(var idBuku: String, var kuantiti: Int)
data class Pembeli(var id: String, var nama: String, var katalaluan: String)

class Buku(
    @BsonId
    var id: ObjectId?, var tajuk: String, var harga: Float
) {

    constructor() : this(null, "tadak tajuk", 0.0f)

}
