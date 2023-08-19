package com.example.prepaidcardsdk.data.model.resp

data class CardDataResponse(
    val status: String,
    val statusDesc: String,
    val viewcardresponseWrapper: ViewcardresponseWrapper
)