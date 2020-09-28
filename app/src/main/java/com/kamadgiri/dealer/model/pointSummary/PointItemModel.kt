package com.kamadgiri.dealer.model.pointSummary

import java.io.Serializable

class PointItemModel: Serializable {

    lateinit var _id: String
    lateinit var orderId: String
    lateinit var userId: String
    lateinit var createdAt: String
    lateinit var lastupdatedpoint: String
    lateinit var description: String
    lateinit var transactionType: String
    lateinit var pointValue: String
    lateinit var source: String
    lateinit var transactionStatus: String
    lateinit var transactionDate: String
    lateinit var walletId: String
    lateinit var updatedAt: String
    lateinit var mId: String
    var __v: Int

    constructor(
        _id: String,
        orderId: String,
        userId: String,
        createdAt: String,
        lastupdatedpoint: String,
        description: String,
        transactionType: String,
        pointValue: String,
        source: String,
        transactionStatus: String,
        transactionDate: String,
        walletId: String,
        updatedAt: String,
        mId: String,
        __v: Int
    ) {
        this._id = _id
        this.orderId = orderId
        this.userId = userId
        this.createdAt = createdAt
        this.lastupdatedpoint = lastupdatedpoint
        this.description = description
        this.transactionType = transactionType
        this.pointValue = pointValue
        this.source = source
        this.transactionStatus = transactionStatus
        this.transactionDate = transactionDate
        this.walletId = walletId
        this.updatedAt = updatedAt
        this.mId = mId
        this.__v = __v
    }
}