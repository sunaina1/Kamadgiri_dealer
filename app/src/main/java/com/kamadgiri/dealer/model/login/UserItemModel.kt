package com.kamadgiri.dealer.model.login

import com.google.gson.Gson
import java.io.Serializable

class UserItemModel: Serializable {
    var _id: String
    var mobileNumber: String
    var name: String
    var email: String

    constructor(_id: String, mobileNumber: String, name: String, email: String) {
        this._id = _id
        this.mobileNumber = mobileNumber
        this.name = name
        this.email = email
    }
    fun create(jsonString: String?): UserItemModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, UserItemModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }

}