package com.belajar

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.slf4j.LoggerFactory

class DataManagerMongo {

    private val log = LoggerFactory.getLogger(DataManagerMongo::class.java)

    private val pangkalan: MongoDatabase
    private val koleksiBuku: MongoCollection<Buku>

    init {
        (LoggerFactory.getILoggerFactory() as LoggerContext).getLogger("org.mongodb.driver").level = Level.ERROR
        val pojoCodecRegistry: CodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build())

        val codecRegistry: CodecRegistry =
            fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry)

        val clientSettings: MongoClientSettings = MongoClientSettings.builder()
            .codecRegistry(codecRegistry)
            .build()

        val mongoClient = MongoClients.create(clientSettings)
        pangkalan = mongoClient.getDatabase("kedaibuku")
        koleksiBuku = pangkalan.getCollection(Buku::class.java.simpleName, Buku::class.java)
//        initBukubuku()
    }

    private fun initBukubuku() {
        koleksiBuku.insertOne(Buku(null, "Cara tanam pisang", 100.0f))
        koleksiBuku.insertOne(Buku(null, "Cara tanam betik", 90.0f))
        koleksiBuku.insertOne(Buku(null, "Cara tanam nanas", 80.0f))
        koleksiBuku.insertOne(Buku(null, "Cara tanam kelapa", 70.0f))
        koleksiBuku.insertOne(Buku(null, "Cara tanam pelam", 80.0f))
        koleksiBuku.insertOne(Buku(null, "Cara tanam oren", 60.0f))
    }

    fun tambahBuku(buku: Buku): Buku {
        koleksiBuku.insertOne(buku)
        return buku
    }

    fun kemaskiniBuku(buku: Buku): Buku? {
        val baru = koleksiBuku.find(eq("_id", buku.id)).first()
        baru?.tajuk = buku.tajuk
        baru?.harga = buku.harga
        return baru
    }

    fun padamBuku(idBuku: String): Buku? {
        val bukuDipadam = koleksiBuku.find(eq("_id", idBuku)).first()
        koleksiBuku.deleteOne(eq("_id", idBuku))
        return bukuDipadam
    }

    fun semuaBuku() = koleksiBuku.find().toList()

    fun susunan(sisih: String, menaik: Boolean): List<Buku> {
        val helaian = 1
        val saizmuka = 10
        val susunatur: Int = if (menaik) 1 else -1
        return koleksiBuku.find()
            .sort(Document(mapOf(Pair(sisih, susunatur), Pair("_id", -1))))
            .skip(helaian - 1)
            .limit(saizmuka)
            .toList()
    }
}
