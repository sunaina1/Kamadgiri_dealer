package com.kamadgiri.dealer.model.register

import com.google.gson.Gson
import java.io.Serializable

class RegisterResponseModel: Serializable {
    var statusCode: Int
    var msg: String

    constructor(statusCode: Int, msg: String) {
        this.statusCode = statusCode
        this.msg = msg
    }

    fun create(jsonString: String?): RegisterResponseModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, RegisterResponseModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}