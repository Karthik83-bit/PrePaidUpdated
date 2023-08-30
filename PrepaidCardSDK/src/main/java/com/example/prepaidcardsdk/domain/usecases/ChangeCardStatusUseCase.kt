package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeCardStatusUseCase @Inject constructor(val repo:Repository) {
    fun invoke(otp: String, status: String, params: String,): Flow<NetworkResponse<ChangeStatusResponseModel>> {
       return repo.changeCardStatus(otp,status,params)
    }
}