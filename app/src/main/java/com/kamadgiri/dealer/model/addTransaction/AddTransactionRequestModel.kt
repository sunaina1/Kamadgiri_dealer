package com.kamadgiri.dealer.model.addTransaction

import java.io.Serializable

class AddTransactionRequestModel: Serializable {

    lateinit var productId: String
    lateinit var userId: String

    lateinit var labelNo: String
    lateinit var crop: String
    lateinit var lotNo: String
    lateinit var productName: String
    lateinit var batchNo: String
    lateinit var packetWeight: String
    lateinit var packetsInside: String
    lateinit var bagWeight: String
constructor()
    constructor(
        productId: String,
        userId: String,
        labelNo: String,
        crop: String,
        lotNo: String,
        productName: String,
        batchNo: String,
        packetWeight: String,
        packetsInside: String,
        bagWeight: String
    ) {
        this.productId = productId
        this.userId = userId
        this.labelNo = labelNo
        this.crop = crop
        this.lotNo = lotNo
        this.productName = productName
        this.batchNo = batchNo
        this.packetWeight = packetWeight
        this.packetsInside = packetsInside
        this.bagWeight = bagWeight
    }
}