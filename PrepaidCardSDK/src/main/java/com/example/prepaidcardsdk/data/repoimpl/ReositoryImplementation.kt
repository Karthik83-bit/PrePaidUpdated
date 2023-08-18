package com.example.prepaidcardsdk.data.repoimpl

import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.SetPinRequestModel
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.data.src.APIService
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(val apiService: APIService):Repository {
    override fun setPin(encPin: String): Flow<NetworkResponse<SetPinResponse>> =flow{
        emit(NetworkResponse.Loading())
        try{
            val reqBody= SetPinRequestModel(encryptPin = encPin, cardRefId = "168")
            val resp=apiService.setPin(reqBody = reqBody)
            if(resp.isSuccessful){
                if(resp.body()!=null){
                    val statusDescription=
                    emit(
                        NetworkResponse.Success(resp.body()!!))
                }
                else{
                    emit(NetworkResponse.Erroe("EMPTY RESPONSE RETURNED"))
                }

            }
            else{
                val errJson=JSONObject(resp.errorBody().toString())
                val errStr=errJson.getString("error")
                emit(NetworkResponse.Erroe(errStr?:"Something Went Wrong"))
            }
        }catch(e:Exception){
            emit(NetworkResponse.Erroe(e.message?:e.localizedMessage))
        }

    }

    override fun cardData(url: String): Flow<NetworkResponse<CardDataResponse>> = flow{
        emit(NetworkResponse.Loading())
        try{
            val reqBody= CardDataRequestModel(cardRefId = "167", customerId = "1287208")
            val resp=apiService.cardData(requestModel = reqBody)
            if(resp.isSuccessful){
                if(resp.body()!=null){

                }
                else{
                    emit(NetworkResponse.Erroe("EMPTY RESPONSE RETURNED"))
                }

            }
            else{
                val errJson=JSONObject(resp.errorBody().toString())
                val errStr=errJson.getString("error")
                emit(NetworkResponse.Erroe(errStr?:"Something Went Wrong"))
            }
        }catch(e:Exception){
            emit(NetworkResponse.Erroe(e.message?:e.localizedMessage))
        }
    }
}