package com.example.prepaidcardsdk.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.data.model.req.CardDataRequestModel
import com.example.prepaidcardsdk.data.model.req.ViewCardDataReqModel
import com.example.prepaidcardsdk.data.model.resp.CardDataByCustomerResp
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.ViewcardresponseWrapperX
import com.example.prepaidcardsdk.domain.usecases.CardDataByCustomerUseCase
import com.example.prepaidcardsdk.domain.usecases.CardDataUseCase
import com.example.prepaidcardsdk.utils.EncryptDecrypt
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDataViewModel @Inject constructor(
    val cardDataUseCase: CardDataUseCase,
    val cardDataByCustomerUseCase: CardDataByCustomerUseCase
) :

    ViewModel() {

    val isLoading = mutableStateOf(false)
    var isError: MutableState<Boolean> = mutableStateOf(false)
    var errorMessage: MutableState<String> = mutableStateOf("")
    var cardList: MutableState<List<ViewcardresponseWrapperX>?> = mutableStateOf(emptyList())
    var destination:MutableState<String> =mutableStateOf(Destination.VIEW_CARDS_SCREEN)

    @RequiresApi(Build.VERSION_CODES.O)
    fun cardDataByCustomer() {

        viewModelScope.launch {
            handleFlow<CardDataByCustomerResp>(
                response = cardDataByCustomerUseCase.invoke(
                    "http://35.200.225.250:8080/cardissuer/cms/viewCarddataByCustomer",
                    requestModel = CardDataRequestModel()
                ),
                onLoading = { isLoading.value = it },
                onFailure = {
                    isError.value = true
                    errorMessage.value = it
                },
                onSuccess = {
                    cardList.value = it?.viewcardresponseWrapper
                })

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun viewCardData(customerId:String,cardRefId:String,onComplete: (CardDataResponse?) -> Unit) {
        viewModelScope.launch {
            handleFlow<CardDataResponse>(
                response = cardDataUseCase.invoke(
                    "http://35.200.225.250:8080/cardissuer/cms/viewCarddata",
                    requestModel = ViewCardDataReqModel(cardRefId = SDK_CONSTANTS.cardRefId?:"", customerId = customerId)
                ),
                onSuccess = {
                    onComplete(it)
                    if (it != null) {
                        val enc_cardno=it.viewcardresponseWrapper.encryptedCard
                        val cardno=EncryptDecrypt.decryptData(enc_cardno.toByteArray(Charsets.UTF_8), key = EncryptDecrypt.key)
                        SDK_CONSTANTS.cardNumber=cardno
                        SDK_CONSTANTS.isBlock=it.viewcardresponseWrapper.isBlock
                        SDK_CONSTANTS.isActive=it.viewcardresponseWrapper.isActive
                        SDK_CONSTANTS.isHotList=it.viewcardresponseWrapper.isHotlist
                        SDK_CONSTANTS.isVirtual=it.viewcardresponseWrapper.isVirtual
                        SDK_CONSTANTS.cardUser=it.viewcardresponseWrapper.nameonCard
                        SDK_CONSTANTS.expiryDate=it.viewcardresponseWrapper.expiryDate
                    }

                            },
                onLoading = { isLoading.value = it },
                onFailure = {
                    isError.value = true
                    errorMessage.value = it
                }
            )
        }
    }
}