package com.kamadgiri.dealer.model.transactionVolume

import com.google.gson.Gson
import java.io.Serializable

class TransactionVolumeRequestModel: Serializable {
    lateinit var userId: String
    constructor()
    constructor(userId: String) {
        this.userId = userId
    }

    fun create(jsonString: String?): TransactionVolumeRequestModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, TransactionVolumeRequestModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}