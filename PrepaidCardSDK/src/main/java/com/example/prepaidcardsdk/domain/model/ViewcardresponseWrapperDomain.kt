package com.example.prepaidcardsdk.domain.model

data class ViewcardresponseWrapperDomain(
    val cardRefId: String,
    val cardType: String,
    val decryptedCard: String?,
    val encryptedCvv: Any,
    val expiryDate: String,
    val isActive: Boolean,
    val isBlock: Boolean,
    val isHotlist: Boolean,
    val lastfourDigit: String,
    val nameonCard: String,
    val productName: String
)
