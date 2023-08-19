package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetPinUseCase @Inject constructor(val repo:Repository) {
    fun invoke(pin: String, otp: String): Flow<NetworkResponse<ResetPinResponseModel>> {
       return repo.resetPin(pin,otp)
    }
}