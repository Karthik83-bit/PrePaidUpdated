package com.example.prepaidcardsdk.data.model.req

data class ResetPinRequestModel(
    val cardRefId: String?="168",
    val encryptPin: String?,
    val otp: String?
)