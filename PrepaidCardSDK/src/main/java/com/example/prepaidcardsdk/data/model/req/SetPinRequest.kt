package com.example.prepaidcardsdk.data.model.req

data class SetPinRequestModel(
    val cardRefId: String?="168",
    val encryptPin: String?="1072"
)