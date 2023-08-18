package com.example.prepaidcardsdk.utils

data class GeneralUiState<T>(
    val isLoading:Boolean?=false,
    val isError:Boolean?=false,
    var errString:String?="",
    val data:T?=null
)
