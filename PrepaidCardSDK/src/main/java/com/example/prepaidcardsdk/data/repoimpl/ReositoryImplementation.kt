package com.example.prepaidcardsdk.data.repoimpl

import com.example.prepaidcardsdk.data.model.SetPinResponse
import com.example.prepaidcardsdk.data.src.APIService
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(val apiService: APIService):Repository {
    override fun setPin(): Flow<NetworkResponse<SetPinResponse>> =flow{
        try{
            val resp=apiService.setPin()
            if(resp.isSuccessful){
                if(resp.body()!=null){
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
}