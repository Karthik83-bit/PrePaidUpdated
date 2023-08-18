package com.example.prepaidcardsdk.utils

sealed class NetworkResponse<T>(val dat:T?=null,val message:String?=null){
    class Success<T>(val data:T):NetworkResponse<T>(data)
    class Loading<T>(val isLoading: Boolean) : NetworkResponse<T>(null)
    class Error<T>(val msg:String):NetworkResponse<T>(message = msg)
}
