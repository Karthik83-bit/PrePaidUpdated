package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.resp.ViewCvvResponseModel
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ViewCvvUseCase @Inject constructor(val repo:Repository) {
    fun invoke(otp:String): Flow<NetworkResponse<ViewCvvResponseModel>> {
        return repo.viewCvv(otp = otp)
    }
}