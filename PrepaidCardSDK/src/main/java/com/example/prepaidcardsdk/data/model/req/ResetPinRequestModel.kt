package com.example.prepaidcardsdk.data.model.req

data class ResetPinRequestModel(
    val cardRefId: String?="98102384501",
    val encryptPin: String?,
    val otp: String?
)