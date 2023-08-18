package com.example.prepaidcardsdk.data.repoimpl

import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.ChangeStatusRequestModel
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel

import com.example.prepaidcardsdk.data.model.req.SetPinRequestModel
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.data.src.APIService
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import com.example.prepaidcardsdk.utils.handleFlowResponse
import kotlinx.coroutines.flow.Flow
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

    override fun cardDataStatus(url: String): Flow<NetworkResponse<CardDataResponse>> {
        val reqBody = CardDataRequestModel("1287208", "167")
        return handleFlowResponse(call = {
            apiService.cardData(reqBody)
        },{it})
    }


}