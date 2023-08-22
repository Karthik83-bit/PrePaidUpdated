package com.example.prepaidcardsdk.data.model.resp

data class ViewCvvResponseModel(
    val cvv: String,
    val status: String,
    val statusDesc: String
)