package com.example.prepaidcardsdk.data.model.req

data class ChangeStatusRequestModel(
    val cardRefId: String,
    val cardStatus: String
)