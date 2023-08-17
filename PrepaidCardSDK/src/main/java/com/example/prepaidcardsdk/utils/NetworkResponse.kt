package com.example.prepaidcardsdk.utils

sealed class NetworkResponse<T>(val dat:T?=null,val message:String?=null){
    class Success<T>(val data:T):NetworkResponse<T>(data)
    class Loading<T>():NetworkResponse<T>()
    class Erroe<T>(val msg:String):NetworkResponse<T>(message = msg)
}
