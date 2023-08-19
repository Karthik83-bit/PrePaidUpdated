package com.example.prepaidcardsdk.domain.repo

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.resp.CardDataByCustomerResp
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel

import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

interface Repository {

    fun setPin(encPin: String): Flow<NetworkResponse<SetPinResponse>>
    fun changeCardStatus():Flow<NetworkResponse<ChangeStatusResponseModel>>
    fun cardDataStatus(url: String, requestModel: CardDataRequestModel): Flow<NetworkResponse<CardDataResponse>>
    fun cardDataByCustomerStatus(url: String, requestModel: CardDataRequestModel): Flow<NetworkResponse<CardDataByCustomerResp>>

    fun resetPin(pin:String,otp:String):Flow<NetworkResponse<ResetPinResponseModel>>

}