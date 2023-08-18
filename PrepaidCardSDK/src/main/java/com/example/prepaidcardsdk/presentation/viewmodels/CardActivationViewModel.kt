package com.example.prepaidcardsdk.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.data.model.ChangeStatusResponseModel
import com.example.prepaidcardsdk.data.model.SetPinResponse
import com.example.prepaidcardsdk.domain.usecases.ChangeCardStatusUseCase
import com.example.prepaidcardsdk.utils.GeneralUiState
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardActivationViewModel @Inject constructor(val changeCardStatusUseCase: ChangeCardStatusUseCase) :
    ViewModel() {
//    val uiState = mutableStateOf(GeneralUiState<ChangeStatusResponseModel>())
    var cardActivationToggleState =
        mutableStateOf(false)

    var isLoading: MutableState<Boolean> = mutableStateOf(false)
    var isError: MutableState<Boolean> = mutableStateOf(false)
    var errorMessage: MutableState<String> = mutableStateOf("")
    var response:MutableState<ChangeStatusResponseModel?> = mutableStateOf(null)
    fun changeCardStatus(onComplete:(ChangeStatusResponseModel?)->Unit) {
        viewModelScope.launch {
            handleFlow<ChangeStatusResponseModel>(response = changeCardStatusUseCase.invoke(),
                onLoading = {
                    isLoading.value = it
                },
                onFailure = {
                    isError.value = true
                    errorMessage.value = it
                },
                onSuccess = {
                    onComplete(it)
                })
        }

    }
}