package com.example.prepaidcardsdk.data.model

data class ChangeStatusRequestModel(
    val cardRefId: String,
    val cardStatus: String
)