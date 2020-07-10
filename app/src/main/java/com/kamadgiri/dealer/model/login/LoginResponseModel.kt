package com.kamadgiri.dealer.model.login

import com.google.gson.Gson
import java.io.Serializable

class LoginResponseModel: Serializable {
    var statusCode: Int
    var msg: String
    var token: String
    var user: UserItemModel

    constructor(statusCode: Int, msg: String, token: String, user: UserItemModel) {
        this.statusCode = statusCode
        this.msg = msg
        this.token = token
        this.user = user
    }

    fun create(jsonString: String?): LoginResponseModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, LoginResponseModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}