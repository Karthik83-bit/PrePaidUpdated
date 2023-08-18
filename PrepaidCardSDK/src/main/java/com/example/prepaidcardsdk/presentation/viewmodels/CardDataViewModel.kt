package com.example.prepaidcardsdk.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcardsdk.data.model.resp.CardDataResponse
import com.example.prepaidcardsdk.data.model.resp.ChangeStatusResponseModel
import com.example.prepaidcardsdk.domain.usecases.CardDataUseCase
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDataViewModel @Inject constructor(
    val cardDataUseCase: CardDataUseCase):

    ViewModel() {

    val isLoading= mutableStateOf(false)
    var isError: MutableState<Boolean> = mutableStateOf(false)
    var errorMessage: MutableState<String> = mutableStateOf("")
    var response:MutableState<ChangeStatusResponseModel?> = mutableStateOf(null)

    @RequiresApi(Build.VERSION_CODES.O)
    fun cardData(onComplete: (CardDataResponse?) -> Unit){

        viewModelScope.launch {
            handleFlow<CardDataResponse>(response = cardDataUseCase.invoke(), onLoading = {
                isLoading.value = it
            }, onFailure = {
                isError.value = true
                errorMessage.value = it
            }, onSuccess = {

                onComplete(it)
            })

        }
    }
}