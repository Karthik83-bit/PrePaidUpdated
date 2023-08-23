package com.example.prepaidcardsdk.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcardsdk.data.model.req.VerifyOtpReq
import com.example.prepaidcardsdk.data.model.resp.VerifyOtpResp
import com.example.prepaidcardsdk.domain.usecases.VerifyOTPUseCase
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyOTPViewModel @Inject constructor(val verifyOTPUseCase: VerifyOTPUseCase): ViewModel() {

    var verifyOtp= mutableStateOf("")
    var mobilenum = mutableStateOf("")
    var isLoading:MutableState<Boolean> = mutableStateOf(false)
    var isError:MutableState<Boolean> = mutableStateOf(false)
    var errorMessage:MutableState<String> = mutableStateOf("")
    var destination:MutableState<String> =mutableStateOf("")


    @RequiresApi(Build.VERSION_CODES.O)
    fun VerifyOtp(onSuccess: (VerifyOtpResp)->Unit) {

            handleFlow<VerifyOtpResp>(
                response = verifyOTPUseCase.invoke(verifyOtpReq = VerifyOtpReq("9128663078", verifyOtp.value)),
                onLoading = { isLoading.value = it },
                onFailure = {
                    isError.value = true
                    isLoading.value = false
                    errorMessage.value = it
                },
                onSuccess = {
                    if (it != null) {
                        onSuccess(it)
                    }
                })

    }
}