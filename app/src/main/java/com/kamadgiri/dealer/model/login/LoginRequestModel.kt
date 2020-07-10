package com.kamadgiri.dealer.model.login

import com.google.gson.Gson
import java.io.Serializable

class LoginRequestModel: Serializable{
    lateinit var email: String
    lateinit var password: String

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }

    constructor()


    fun create(jsonString: String?): LoginRequestModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, LoginRequestModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }

}