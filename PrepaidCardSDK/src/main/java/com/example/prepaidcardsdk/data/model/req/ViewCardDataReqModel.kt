package com.example.prepaidcardsdk.data.model.req

data class ViewCardDataReqModel(
    val cardRefId: String,
    val otp: String,
    val mobileNumber: String,
)