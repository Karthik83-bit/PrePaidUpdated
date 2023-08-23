package com.example.prepaidcardsdk.data.model.resp

data class VerifyOtpResp(
    val customerResponse: CustomerResponse,
    val status: String,
    val statusDesc: String
)