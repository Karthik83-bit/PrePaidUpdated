package com.example.prepaidcardsdk.data.model.req

data class ViewCvvRequestModel(
    val cardRefNumber: String,
    val otp: String,
    val mobileNumber:String
)