package com.example.prepaidcardsdk.data.model.resp

data class CustomerResponse(
    val address: String,
    val address1: String,
    val city: String,
    val corporateName: String,
    val country: String,
    val createdDate: String,
    val dateOfBirth: String,
    val email: String,
    val gender: String,
    val kycType: String,
    val mobileNumber: Long,
    val name: String,
    val ovdnumber: String,
    val pincode: Int,
    val programManagerName: String,
    val state: String,
    val updatedDate: String,
    val userId: Int
)