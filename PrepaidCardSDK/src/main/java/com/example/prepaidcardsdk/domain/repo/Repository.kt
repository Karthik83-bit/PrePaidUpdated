package com.example.prepaidcardsdk.domain.repo

import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.ViewCardDataReqModel
import com.example.prepaidcardsdk.data.model.resp.CardDataByCustomerResp
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel

import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun setPin(encPin: String): Flow<NetworkResponse<SetPinResponse>>
    fun changeCardStatus():Flow<NetworkResponse<ChangeStatusResponseModel>>
    fun cardDataStatus(url: String, requestModel: ViewCardDataReqModel): Flow<NetworkResponse<CardDataResponse>>
    fun cardDataByCustomerStatus(url: String, requestModel: CardDataRequestModel): Flow<NetworkResponse<CardDataByCustomerResp>>

    fun resetPin(pin:String,otp:String):Flow<NetworkResponse<ResetPinResponseModel>>

}