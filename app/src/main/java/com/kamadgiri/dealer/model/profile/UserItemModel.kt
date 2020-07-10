package com.kamadgiri.dealer.model.profile

import java.io.Serializable

class UserItemModel: Serializable {
    lateinit var name: String
    lateinit var email: String
    lateinit var mobileNumber: String
    lateinit var village: String
    lateinit var city: String
    lateinit var district: String
    lateinit var state: String
    lateinit var vscCode: String
    lateinit var _id: String
    var __v: Int
    lateinit var password: String
    lateinit var createdAt: String
    lateinit var updatedAt: String

    constructor(
        name: String,
        email: String,
        mobileNumber: String,
        village: String,
        city: String,
        district: String,
        state: String,
        vscCode: String,
        _id: String,
        __v: Int,
        password: String,
        createdAt: String,
        updatedAt: String
    ) {
        this.name = name
        this.email = email
        this.mobileNumber = mobileNumber
        this.village = village
        this.city = city
        this.district = district
        this.state = state
        this.vscCode = vscCode
        this._id = _id
        this.__v = __v
        this.password = password
        this.createdAt = createdAt
        this.updatedAt = updatedAt
    }
}