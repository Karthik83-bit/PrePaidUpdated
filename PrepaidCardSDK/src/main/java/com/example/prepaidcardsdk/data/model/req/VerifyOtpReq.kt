package com.example.prepaidcardsdk.data.model.req

data class VerifyOtpReq(
    val mobileNumber: String,
    val otp: String
)