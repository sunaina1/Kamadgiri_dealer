package com.kamadgiri.dealer.model.pointSummary

import com.google.gson.Gson
import java.io.Serializable

class PointSummaryResponseModel: Serializable {

    var statusCode: Int
    lateinit var msg: String
    lateinit var balance: String
    lateinit var list: ArrayList<PointItemModel>

    constructor(statusCode: Int, msg: String, balance: String, list: ArrayList<PointItemModel>) {
        this.statusCode = statusCode
        this.msg = msg
        this.balance = balance
        this.list = list
    }


    fun create(jsonString: String?): PointSummaryResponseModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, PointSummaryResponseModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}