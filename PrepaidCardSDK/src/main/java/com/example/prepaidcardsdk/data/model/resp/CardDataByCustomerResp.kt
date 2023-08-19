package com.example.prepaidcardsdk.data.model.resp

data class CardDataByCustomerResp(
    val status: String,
    val statusDesc: String,
    val viewcardresponseWrapper: List<ViewcardresponseWrapperX>
)