package com.example.prepaidcardsdk.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.domain.usecases.ResetPinUseCase
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageCardViewModel @Inject constructor(val resetPinUseCase: ResetPinUseCase) :ViewModel(){

    var isLoading: MutableState<Boolean> = mutableStateOf(false)

    var isError: MutableState<Boolean> = mutableStateOf(false)
    var errorMessage: MutableState<String> = mutableStateOf("")
    var response: MutableState<SetPinResponse?> = mutableStateOf(null)

    var enterPin= mutableStateOf("")
    var reenterPin= mutableStateOf("")
    var resetPinOtp= mutableStateOf("")

    fun resetPin(onSucess:(ResetPinResponseModel)->Unit){
        handleFlow(resetPinUseCase.invoke(pin =reenterPin.value, otp =resetPinOtp.value), onLoading ={
            isLoading.value=it
        } ,
            onFailure = {
                isError.value=true
                isLoading.value=false
                errorMessage.value=it
            },
            onSuccess = {
                if(it!=null){
                    onSucess(it)
                }

            }
        )
    }
}