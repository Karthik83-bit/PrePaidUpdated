package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.req.VerifyOtpReq
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.data.model.resp.VerifyOtpResp
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyOTPUseCase @Inject constructor(val repository: Repository) {
    fun invoke(verifyOtpReq: VerifyOtpReq): Flow<NetworkResponse<VerifyOtpResp>> {
        return repository.verifyOtp(verifyOtpReq)
    }
}