package com.kamadgiri.dealer.model.profile

import com.google.gson.Gson
import com.kamadgiri.dealer.model.login.LoginRequestModel
import java.io.Serializable

class UpdateProfileRequestModel: Serializable {
    lateinit var _id: String
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
        _id: String,
        name: String,
        email: String,
        mobileNumber: String,
        village: String,
        city: String,
        district: String,
        state: String,
        vscCode: String
    ) {
        this._id = _id
        this.name = name
        this.email = email
        this.mobileNumber = mobileNumber
        this.village = village
        this.city = city
        this.district = district
        this.state = state
        this.vscCode = vscCode
    }

    fun create(jsonString: String?): UpdateProfileRequestModel? {
        val gson = Gson()
        return gson.fromJson(jsonString, UpdateProfileRequestModel::class.java)
    }

    fun serialize(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }
}