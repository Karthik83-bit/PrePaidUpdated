package com.example.prepaidcardsdk.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newui.components.CardFace
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel
import com.example.prepaidcardsdk.data.model.resp.ViewCvvResponseModel
import com.example.prepaidcardsdk.domain.usecases.ChangeCardStatusUseCase
import com.example.prepaidcardsdk.domain.usecases.ResetPinUseCase
import com.example.prepaidcardsdk.domain.usecases.ViewCvvUseCase
import com.example.prepaidcardsdk.utils.EncryptDecrypt
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageCardViewModel @Inject constructor(val resetPinUseCase: ResetPinUseCase,val viewCvvUseCase:ViewCvvUseCase,val changeCardStatus:ChangeCardStatusUseCase) :ViewModel(){

    val cardDataMask: MutableState<Boolean> = mutableStateOf(true)
    val viewBalanceOtp: MutableState<Boolean> = mutableStateOf(false)
    var isLoading: MutableState<Boolean> = mutableStateOf(false)

    var isError: MutableState<Boolean> = mutableStateOf(false)

    var errorMessage: MutableState<String> = mutableStateOf("")
    val cardFace= mutableStateOf(CardFace.Front)


    var enterPin= mutableStateOf("")
    var reenterPin= mutableStateOf("")
    var navDest= mutableStateOf("")
    var delay= mutableStateOf(500)

    val list = listOf<String>("Services", "Managecard", "Statement", "Usage")

    val clickedState =
        mutableStateOf(list[0])


    var Otp=mutableStateOf("")

    ///uiState
    val resetPinUI= mutableStateOf(false)
    val blockCardUI= mutableStateOf(false)
    val cvvUI= mutableStateOf(false)


    //SheetStates
    val resetPinSheetState= mutableStateOf(false)
    val blockCardSheetState= mutableStateOf(false)
    val resetPinOtpSheetState=mutableStateOf(false)
    val cvvOtpSheetState=mutableStateOf(false)
    val blockOtpSheetState=mutableStateOf(false)
    val viewBalanceOtpSheetState= mutableStateOf(false)
    val serviceRadioState: MutableState<String> =mutableStateOf("")

    val startanim=
        mutableStateOf(false)
    val commingSoonSheet= mutableStateOf(false)

//    val cautionSheetState= mutableStateOf(false)






//    val ReplaceToggleState_d =
//        mutableStateOf(false)
//
//
//
//    val ResetPinToggleState_d =
//        mutableStateOf(false)
//
//
//    val PauseCardToggleState_d =
//        mutableStateOf(false)
//
//
//    val HotListToggleState_d =
//        mutableStateOf(false)
//
//
//
//
//
//    val ReplaceToggleState =
//        mutableStateOf(false)
//
//    val CvvToggleState =
//        mutableStateOf(false)
//    val CvvToggleState_d =
//        mutableStateOf(false)
//
//    val ResetPinToggleState =
//        mutableStateOf(false)
//    val ResetPinOtpState=mutableStateOf(false)
//
//    val PauseCardToggleState =
//        mutableStateOf(SDK_CONSTANTS.isBlock?:false)
//    val PauseCardOtpState=mutableStateOf(false)
//
//    val HotListToggleState =
//        mutableStateOf(SDK_CONSTANTS.isHotList?:false)
//    val HotListOtpState=mutableStateOf(false)
//
//    val EnterOTPToggleState =
//        mutableStateOf(false)
//
//    val DetailsState =
//        mutableStateOf(false)
    val cvvValue= mutableStateOf("")
    val checkBoxState =
        mutableStateOf("")




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
                    if(it.status=="0"){
                        SDK_CONSTANTS.isPinSet=false
                    }
                    onSucess(it)
                }


            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)

    fun viewCvv( onSucesss:(ViewCvvResponseModel)->Unit){
        handleFlow(viewCvvUseCase.invoke(Otp.value),
            onSuccess = {


                if (it != null) {
                    onSucesss(it)
                    if(it.status=="0"){



                        cvvValue.value=EncryptDecrypt.decryptData(it.cvv.toByteArray(Charsets.UTF_8),EncryptDecrypt.key)

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
                errorMessage.value=it
        },
        onLoading = {
            isLoading.value=it

        })
    }

    fun changeCardStatus(
        otp: String = Otp.value,
        status: String,
        params: String,
        onSucesss: (ChangeStatusResponseModel) -> Unit,

    ){

        handleFlow(changeCardStatus.invoke(otp,status,params), onLoading = {
                                                                    isLoading.value=it
        }, onSuccess = {

            if(it!=null){
                if(it.status=="0") {
                    onSucesss(it)

                    if (status.equals("block")) {
                        SDK_CONSTANTS.isBlock = true
                        blockOtpSheetState.value = false
                    } else {
                        SDK_CONSTANTS.isBlock = false
                    }

                    Otp.value = ""
                }
                else{
                    isError.value=true
                    errorMessage.value=it.statusDesc

                }
            }

        }, onFailure = {
            isError.value=true
            errorMessage.value=it

            Otp.value=""
        })
    }
}