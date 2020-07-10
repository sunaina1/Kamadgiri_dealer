package com.kamadgiri.dealer.model.profile

import com.google.gson.Gson
import com.kamadgiri.dealer.model.login.LoginRequestModel
import java.io.Serializable

class UpdateProfileResponseModel: Serializable {

    var statusCode: Int
    lateinit var msg: String
    lateinit var user: UserItemModel

    constructor(statusCode: Int, msg: String, user: UserItemModel) {
        this.statusCode = statusCode
        this.msg = msg
        this.user = user
    }


    fun create(jsonString: String?): UpdateProfileResponseModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, UpdateProfileResponseModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}