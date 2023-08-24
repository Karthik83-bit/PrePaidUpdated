package com.example.prepaidcardsdk.domain.repo

import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.GenerateOTPReq
import com.example.prepaidcardsdk.data.model.req.VerifyOtpReq
import com.example.prepaidcardsdk.data.model.req.ViewCardDataReqModel
import com.example.prepaidcardsdk.data.model.resp.CardDataByCustomerResp
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel
import com.example.prepaidcardsdk.data.model.resp.GenerateOTPResp
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel

import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.data.model.resp.VerifyOtpResp
import com.example.prepaidcardsdk.data.model.resp.ViewCvvResponseModel
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun setPin(encPin: String): Flow<NetworkResponse<SetPinResponse>>
    fun changeCardStatus(otp:String,status:String):Flow<NetworkResponse<ChangeStatusResponseModel>>
    fun cardDataStatus(url: String, requestModel: ViewCardDataReqModel): Flow<NetworkResponse<CardDataResponse>>
    fun cardDataByCustomerStatus(url: String, requestModel: CardDataRequestModel): Flow<NetworkResponse<CardDataByCustomerResp>>

    fun resetPin(pin:String,otp:String):Flow<NetworkResponse<ResetPinResponseModel>>

    fun viewCvv(otp:String):Flow<NetworkResponse<ViewCvvResponseModel>>
    fun verifyOtp(requestModel: VerifyOtpReq):Flow<NetworkResponse<VerifyOtpResp>>
    fun generateOtp(requestModel: GenerateOTPReq): Flow<NetworkResponse<GenerateOTPResp>>

}