package com.example.prepaidcardsdk.data.repoimpl

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.ChangeStatusRequestModel
import com.example.prepaidcardsdk.data.model.req.ResetPinRequestModel
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel

import com.example.prepaidcardsdk.data.model.req.SetPinRequestModel
import com.example.prepaidcardsdk.data.model.resp.CardDataByCustomerResp
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.data.src.APIService
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import com.example.prepaidcardsdk.utils.handleFlowResponse
import kotlinx.coroutines.flow.Flow
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(val apiService: APIService):Repository {
    override fun setPin(encPin: String): Flow<NetworkResponse<SetPinResponse>> {
        val reqBody=SetPinRequestModel(encryptPin = encPin, cardRefId = "168")

        return handleFlowResponse(call = {apiService.setPin(reqBody)},{it})
    }

    override fun changeCardStatus(): Flow<NetworkResponse<ChangeStatusResponseModel>> {
        val reqBody= ChangeStatusRequestModel(cardRefId = "107", cardStatus = "active")

        return handleFlowResponse(call = {apiService.changeStatus(reqBody)},{it})
    }

    override fun cardDataStatus(
        url: String,
        requestModel: CardDataRequestModel
    ): Flow<NetworkResponse<CardDataResponse>> {
        val reqBody = CardDataRequestModel(cardRefId = "1287208", customerId = "181")
        return handleFlowResponse(call = {
            apiService.cardData(url, reqBody)
        },{it})
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun encryptData(data: String, key: ByteArray): String {
        println("Before Encrypt")
        println(data)
        val dataToSend = data.toByteArray(StandardCharsets.UTF_8)
        println(dataToSend)
        println(String(dataToSend))
        var cipher: Cipher? = null
        var encryptedData: ByteArray? = null
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            val secretKeySpec = SecretKeySpec(key, "AES")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
            encryptedData = cipher.doFinal(dataToSend)
            println(encryptedData)
        } catch (e: Exception) {

            e.printStackTrace()
        }
        val encryptedByteValue = Base64.getEncoder().encode(encryptedData)
        return String(encryptedByteValue)
    }

    override fun cardDataByCustomerStatus(
        url: String,
        requestModel: CardDataRequestModel
    ): Flow<NetworkResponse<CardDataByCustomerResp>> {
        val reqBody = CardDataRequestModel(cardRefId = "1287208", customerId = "181")
        return handleFlowResponse(call = {
            apiService.cardDataByCustomer(url, reqBody)
        },{it})
    }
    val key = "ASDFGHJASHJKLQWEASDFGHJASHJKLQWE".toByteArray(StandardCharsets.UTF_8)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun resetPin(pin: String, otp: String): Flow<NetworkResponse<ResetPinResponseModel>> {
        val encPin = encryptData(pin, key)
        val reqbody = ResetPinRequestModel(encryptPin = encPin, otp = otp)
        return handleFlowResponse({ apiService.resetPin(reqbody) }, { it })
    }


}