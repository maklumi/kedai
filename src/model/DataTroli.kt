package com.belajar.model

import com.belajar.Buku
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

class ItemTroli(var bukuid: ObjectId? = null, var buku: Buku = Buku(), var kuantiti: Int = 0, var jumlah: Float = 0.0f)

class Troli(
    @BsonId
    var id: ObjectId? = null,
    var username: String = "",
    var kuantitiTotal: Int = 0,
    var jumlah: Float = 0.0f,
    var entries: MutableList<ItemTroli> = ArrayList()
) {

    fun addBuku(buku: Buku) {
        val find = entries.find { it.buku.id!! == buku.id }
        if (find == null) {
            entries.add(ItemTroli(buku.id, buku, 1, buku.harga))
        } else {
            find.kuantiti += 1
            find.jumlah += buku.harga
        }
        this.kuantitiTotal += 1
        this.jumlah += buku.harga
    }

    fun removeBuku(buku: Buku) {
        val find = entries.find { it.buku.id!! == buku.id }
        if (find == null) {
            return
        } else {
            find.kuantiti -= 1
            find.jumlah -= buku.harga
            if (find.kuantiti <= 0)
                entries.remove(find)
        }
        this.kuantitiTotal -= 1
        this.jumlah -= buku.harga
    }
}