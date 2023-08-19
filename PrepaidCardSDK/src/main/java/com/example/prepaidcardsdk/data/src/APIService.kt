package com.example.prepaidcardsdk.data.src

import com.example.prepaidcardsdk.data.model.req.ChangeStatusRequestModel
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel

import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.SetPinRequestModel
import com.example.prepaidcardsdk.data.model.resp.CardDataByCustomerResp
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url


interface APIService {
    @POST("cms/setPin")
    suspend fun setPin(@Body reqBody: SetPinRequestModel): Response<SetPinResponse>

    @POST("cms/changeCardStatus")
    suspend fun changeStatus(@Body reqBody: ChangeStatusRequestModel): Response<ChangeStatusResponseModel>

    @POST()
    suspend fun cardData(
        @Url url: String,
        @Body requestModel: CardDataRequestModel = CardDataRequestModel()
    ): Response<CardDataResponse>

    @POST()
    suspend fun cardDataByCustomer(
        @Url url: String,
        @Body requestModel: CardDataRequestModel = CardDataRequestModel()
    ): Response<CardDataByCustomerResp>
}