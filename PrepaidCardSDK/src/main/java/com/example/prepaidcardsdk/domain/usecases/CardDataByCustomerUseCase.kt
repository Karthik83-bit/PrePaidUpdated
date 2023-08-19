package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.resp.CardDataByCustomerResp
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardDataByCustomerUseCase @Inject constructor(
   private val repository: Repository
) {
    fun invoke(url: String, requestModel: CardDataRequestModel): Flow<NetworkResponse<CardDataByCustomerResp>> {
        return repository.cardDataByCustomerStatus(url, requestModel)
    }
}