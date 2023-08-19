package com.example.prepaidcardsdk.data.model.resp

data class ViewcardresponseWrapper(
    val cardRefId: String,
    val cardType: String,
    val encryptedCard: String,
    val encryptedCvv: Any,
    val expiryDate: String,
    val isActive: Boolean,
    val isBlock: Boolean,
    val isHotlist: Boolean,
    val lastfourDigit: String,
    val nameonCard: String,
    val productName: String
)