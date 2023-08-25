package com.example.prepaidcardsdk.data.model.req

data class GenerateOTPReq(
    val cardRefid: String,
    val expairyTime: String,
    val mobileNumber: String,
    val params: String
)