package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.req.GenerateOTPReq
import com.example.prepaidcardsdk.data.model.resp.GenerateOTPResp
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateOtpUseCase @Inject constructor(val repository: Repository) {
    fun invoke(generateOTPReq: GenerateOTPReq): Flow<NetworkResponse<GenerateOTPResp>> {
        return repository.generateOtp(generateOTPReq)
    }
}