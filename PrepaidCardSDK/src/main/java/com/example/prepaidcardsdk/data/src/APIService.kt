package com.example.prepaidcardsdk.data.src

import com.example.prepaidcardsdk.data.model.ChangeStatusRequestModel
import com.example.prepaidcardsdk.data.model.ChangeStatusResponseModel
import com.example.prepaidcardsdk.data.model.SetPinRequestModel
import com.example.prepaidcardsdk.data.model.SetPinResponse
import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.SetPinRequestModel
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIService {
    @POST("cms/setPin")
    suspend fun setPin(@Body reqBody :SetPinRequestModel):Response<SetPinResponse>

    @POST("cms/changeCardStatus")
    suspend fun changeStatus(@Body reqBody: ChangeStatusRequestModel):Response<ChangeStatusResponseModel>
    suspend fun setPin(@Body reqBody : SetPinRequestModel = SetPinRequestModel()):Response<SetPinResponse>

    @POST("cms/viewCarddataByCustomer")
    suspend fun cardData(@Body requestModel: CardDataRequestModel = CardDataRequestModel()): Response<CardDataResponse>
}