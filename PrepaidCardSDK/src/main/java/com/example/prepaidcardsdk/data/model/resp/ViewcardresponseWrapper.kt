package com.example.prepaidcardsdk.data.model.resp

data class ViewcardresponseWrapper(
    val cardRefId: String,
    val expiryDate: String,
    val isActive: Boolean,
    val isBlock: Boolean,
    val isHotlist: Boolean,
    val lastfourDigit: String,
    val productName: String
)