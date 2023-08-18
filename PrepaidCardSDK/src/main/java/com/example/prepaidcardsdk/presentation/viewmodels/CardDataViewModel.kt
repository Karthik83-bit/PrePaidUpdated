package com.example.prepaidcardsdk.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.domain.usecases.CardDataUseCase
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardDataViewModel @Inject constructor(val cardDataUseCase: CardDataUseCase):
    com.example.prepaidcard.screens.ViewModel() {

    val errorState= mutableStateOf(
        GeneratePinViewModel.CustomError()
    )
    val isLoading= mutableStateOf(false)
    val pinset= mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.O)
    fun cardData(rootNavController: NavHostController){

        viewModelScope.launch {
            cardDataUseCase.invoke("http://35.200.225.250:8080/cardissuer/cms/viewCarddataByCustomer").collectLatest {
                when(it){
                    is NetworkResponse.Erroe -> {
                        errorState.value= GeneratePinViewModel.CustomError(true, it.msg)
                    }
                    is NetworkResponse.Loading -> {
                        isLoading.value=true
                    }
                    is NetworkResponse.Success -> {
                        isLoading.value=false
                        pinset.value=true
                        rootNavController.navigate(Destination.VIEW_CARDS_1)
                    }
                }
            }

        }
    }
}