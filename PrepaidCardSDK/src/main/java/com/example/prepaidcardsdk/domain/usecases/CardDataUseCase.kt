package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.ViewCardDataReqModel
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardDataUseCase @Inject constructor(
    private val repository: Repository
){
    fun invoke(url:String, requestModel: ViewCardDataReqModel): Flow<NetworkResponse<CardDataResponse>>{
        return repository.cardDataStatus(url, requestModel)
    }
}