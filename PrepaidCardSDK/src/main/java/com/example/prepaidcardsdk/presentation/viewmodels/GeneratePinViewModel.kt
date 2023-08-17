package com.example.prepaidcardsdk.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcardsdk.domain.usecases.SetPinUseCase
import com.example.prepaidcardsdk.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratePinViewModel @Inject constructor(val setPinUseCase: SetPinUseCase):ViewModel() {
    val enterPin= mutableStateOf("")
    val reenterPin= mutableStateOf("")
    val pinset= mutableStateOf(false)
    val errorState= mutableStateOf(
        CustomError()
    )

    fun setPin(){
        viewModelScope.launch {
            setPinUseCase.invoke().collectLatest {
                when(it){
                    is NetworkResponse.Erroe -> {
                        errorState.value=CustomError(true,it.msg)
                    }
                    is NetworkResponse.Loading -> {}
                    is NetworkResponse.Success -> {
                        pinset.value=true
                    }
                }
            }
        }

    }


}

data class CustomError(val isError: Boolean?=false, val errStr: String?="")
