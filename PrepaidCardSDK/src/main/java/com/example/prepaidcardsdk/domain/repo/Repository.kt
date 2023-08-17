package com.example.prepaidcardsdk.domain.repo

import com.example.prepaidcardsdk.data.model.SetPinResponse
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun setPin(): Flow<NetworkResponse<SetPinResponse>>
}