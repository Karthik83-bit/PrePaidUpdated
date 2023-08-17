package com.example.prepaidcardsdk.data.src

import com.example.prepaidcardsdk.data.model.SetPinRequestModel
import com.example.prepaidcardsdk.data.model.SetPinResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIService {
    @POST("cms/setPin")
    suspend fun setPin(@Body reqBody :SetPinRequestModel= SetPinRequestModel()):Response<SetPinResponse>
}