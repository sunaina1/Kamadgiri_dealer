package com.kamadgiri.dealer.model.profile

import com.google.gson.Gson
import com.kamadgiri.dealer.model.register.RegisterRequestModel
import java.io.Serializable

class GetProfileResponseModel: Serializable {

     var statusCode: Int
    lateinit var msg: String
    lateinit var user: UserItemModel

    constructor(statusCode: Int, msg: String, user: UserItemModel) {
        this.statusCode = statusCode
        this.msg = msg
        this.user = user
    }


    fun create(jsonString: String?): GetProfileResponseModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, GetProfileResponseModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}