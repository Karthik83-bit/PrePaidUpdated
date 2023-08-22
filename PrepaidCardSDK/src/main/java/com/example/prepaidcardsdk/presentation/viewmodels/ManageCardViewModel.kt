package com.example.prepaidcardsdk.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.data.model.resp.ViewCvvResponseModel
import com.example.prepaidcardsdk.domain.usecases.ChangeCardStatusUseCase
import com.example.prepaidcardsdk.domain.usecases.ResetPinUseCase
import com.example.prepaidcardsdk.domain.usecases.ViewCvvUseCase
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageCardViewModel @Inject constructor(val resetPinUseCase: ResetPinUseCase,val viewCvvUseCase:ViewCvvUseCase,val changeCardStatus:ChangeCardStatusUseCase) :ViewModel(){

    var isLoading: MutableState<Boolean> = mutableStateOf(false)

    var isError: MutableState<Boolean> = mutableStateOf(false)
    var enterOtpVisible: MutableState<Boolean> = mutableStateOf(false)
    var errorMessage: MutableState<String> = mutableStateOf("")
    var response: MutableState<SetPinResponse?> = mutableStateOf(null)

    var enterPin= mutableStateOf("")
    var reenterPin= mutableStateOf("")

    var Otp=mutableStateOf("")
    var blockCardOtp=mutableStateOf("")
    var hotListCardOtp=mutableStateOf("")
    val cvvMask=mutableStateOf(10.dp)

    val ReplaceToggleState_d =
        mutableStateOf(false)



    val ResetPinToggleState_d =
        mutableStateOf(false)


    val PauseCardToggleState_d =
        mutableStateOf(false)


    val HotListToggleState_d =
        mutableStateOf(false)





    val ReplaceToggleState =
        mutableStateOf(false)

    val CvvToggleState =
        mutableStateOf(false)
    val CvvToggleState_d =
        mutableStateOf(false)

    val ResetPinToggleState =
        mutableStateOf(false)
    val ResetPinOtpState=mutableStateOf(false)

    val PauseCardToggleState =
        mutableStateOf(SDK_CONSTANTS.isBlock?:false)
    val PauseCardOtpState=mutableStateOf(false)

    val HotListToggleState =
        mutableStateOf(SDK_CONSTANTS.isHotList?:false)
    val HotListOtpState=mutableStateOf(false)

    val EnterOTPToggleState =
        mutableStateOf(false)

    val DetailsState =
        mutableStateOf(false)


    fun resetPin(onSucess:(ResetPinResponseModel)->Unit){
        handleFlow(resetPinUseCase.invoke(pin =reenterPin.value, otp =Otp.value), onLoading ={
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

    fun viewCvv(otp:String,onSucesss:(ViewCvvResponseModel)->Unit){
        handleFlow(viewCvvUseCase.invoke(otp),
            onSuccess = {
                if (it != null) {
                    if(it.status=="0"){
                        cvvMask.value=0.dp
                    }
                    else{
                        errorMessage.value=it.statusDesc
                        isError.value=true
                    }
                }
                else{
                    isError.value=true
                    errorMessage.value="Null Body"
                }

        }, onFailure = {
            isError.value=true
                errorMessage.value=""
        },
        onLoading = {
            isLoading.value=true
            isError.value=false
        })
    }

    fun changeCardStatus(otp:String,status:String,onSucesss:(ChangeStatusResponseModel)->Unit){
        handleFlow(changeCardStatus.invoke(otp,status), onLoading = {}, onSuccess = {
            if(it!=null){
                onSucesss(it)
            }

        }, onFailure = {})
    }
}