package com.example.prepaidcardsdk.domain.repo

import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel

import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun setPin(encPin: String): Flow<NetworkResponse<SetPinResponse>>
    fun changeCardStatus():Flow<NetworkResponse<ChangeStatusResponseModel>>
}