package com.kamadgiri.dealer.model.transactionVolume

import com.google.gson.Gson
import java.io.Serializable

class TransactionVolumeResponseModel: Serializable {

    var statusCode: Int
    lateinit var msg: String
    lateinit var transactions: ArrayList<TransactionItemModel>

    constructor(statusCode: Int, msg: String, transactions: ArrayList<TransactionItemModel>) {
        this.statusCode = statusCode
        this.msg = msg
        this.transactions = transactions
    }


    fun create(jsonString: String?): TransactionVolumeResponseModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, TransactionVolumeResponseModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}