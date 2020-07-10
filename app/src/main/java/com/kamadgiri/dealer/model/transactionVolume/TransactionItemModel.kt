package com.kamadgiri.dealer.model.transactionVolume

import java.io.Serializable

class TransactionItemModel: Serializable {
    lateinit var _id: String
    lateinit var productId: String
    lateinit var userId: String
    lateinit var createdAt: String
    lateinit var labelNo: String
    lateinit var crop: String
    lateinit var lotNo: String
    lateinit var productName: String
    lateinit var batchNo: String
    lateinit var packetWeight: String
    lateinit var packetsInside: String
    lateinit var bagWeight: String
    lateinit var updatedAt: String
    var __v: Int


    constructor(
        _id: String,
        productId: String,
        userId: String,
        createdAt: String,
        labelNo: String,
        crop: String,
        lotNo: String,
        productName: String,
        batchNo: String,
        packetWeight: String,
        packetsInside: String,
        bagWeight: String,
        updatedAt: String,
        __v: Int
    ) {
        this._id = _id
        this.productId = productId
        this.userId = userId
        this.createdAt = createdAt
        this.labelNo = labelNo
        this.crop = crop
        this.lotNo = lotNo
        this.productName = productName
        this.batchNo = batchNo
        this.packetWeight = packetWeight
        this.packetsInside = packetsInside
        this.bagWeight = bagWeight
        this.updatedAt = updatedAt
        this.__v = __v
    }
}