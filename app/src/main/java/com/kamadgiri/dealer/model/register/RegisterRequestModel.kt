package com.kamadgiri.dealer.model.register

import com.google.gson.Gson
import com.kamadgiri.dealer.model.login.LoginResponseModel
import java.io.Serializable

class RegisterRequestModel: Serializable {
    lateinit var name: String
    lateinit var email: String
    lateinit var mobileNumber: String
    lateinit var village: String
    lateinit var city: String
    lateinit var district: String
    lateinit var state: String
    lateinit var vscCode: String
constructor()
    constructor(
        name: String,
        email: String,
        mobileNumber: String,
        village: String,
        city: String,
        district: String,
        state: String,
        vscCode: String
    ) {
        this.name = name
        this.email = email
        this.mobileNumber = mobileNumber
        this.village = village
        this.city = city
        this.district = district
        this.state = state
        this.vscCode = vscCode
    }

    fun create(jsonString: String?): RegisterRequestModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, RegisterRequestModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}