package com.example.prepaidcardsdk.presentation.viewmodels

//import android.util.Base64
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcardsdk.data.model.resp.ResetPinResponseModel
import com.example.prepaidcardsdk.data.model.resp.SetPinResponse
import com.example.prepaidcardsdk.domain.usecases.ResetPinUseCase
import com.example.prepaidcardsdk.domain.usecases.SetPinUseCase
import com.example.prepaidcardsdk.utils.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

@HiltViewModel
class GeneratePinViewModel @Inject constructor(
    val resetPinUseCase: ResetPinUseCase,

) : ViewModel() {


    var username = mutableStateOf("dbehera56@gmail.com")
    var monthlyLimit = mutableStateOf("₹ 5,00,000.00")
    var cardLimit = mutableStateOf("₹ 2,00,000.00")

    val enterPin = mutableStateOf("")
    val reenterPin = mutableStateOf("")

    //    val pinset= mutableStateOf(false)
    val mask = mutableStateOf(10.dp)

    //    val errorState= mutableStateOf(
//        CustomError()
//    )
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
    var isError: MutableState<Boolean> = mutableStateOf(false)
    var errorMessage: MutableState<String> = mutableStateOf("")
    var response: MutableState<SetPinResponse?> = mutableStateOf(null)
    var otp:MutableState<String> = mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.O)
    fun encryptData(data: String, key: ByteArray): String {
        println("Before Encrypt")
        println(data)
        val dataToSend = data.toByteArray(StandardCharsets.UTF_8)
        println(dataToSend)
        println(String(dataToSend))
        var cipher: Cipher? = null
        var encryptedData: ByteArray? = null
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            val secretKeySpec = SecretKeySpec(key, "AES")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
            encryptedData = cipher.doFinal(dataToSend)
            println(encryptedData)
        } catch (e: Exception) {

            e.printStackTrace()
        }
        val encryptedByteValue = Base64.getEncoder().encode(encryptedData)
        return String(encryptedByteValue)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setPin(onComplete: (ResetPinResponseModel?) -> Unit) {
        val key = "ASDFGHJASHJKLQWEASDFGHJASHJKLQWE".toByteArray(StandardCharsets.UTF_8)
        val enc = encryptData(reenterPin.value, key)




        viewModelScope.launch {

            handleFlow(response = resetPinUseCase.invoke(enc,otp.value), onLoading = {
                isLoading.value = it
            }, onFailure = {
                isError.value = true
                errorMessage.value = it
            }, onSuccess = {

                onComplete(it)
            })

//                when(it){
//                    is NetworkResponse.Error -> {
//                        isLoading.value= false
//                        errorState.value=CustomError(true,it.msg)
//                    }
//                    is NetworkResponse.Loading -> {
//                        isLoading.value=true
//                    }
//                    is NetworkResponse.Success -> {
//                        isLoading.value=false
//                        if(it.data.statusDesc.equals("pin offset successfully generated")&&it.data.status.equals("0")){
//
//
//                            rootNavController.navigate(Destination.ENTER_OTP_SCREEN)
//                        }
//                        else{
//                            rootNavController.popBackStack()
//                        }
//
//
//                    }
//                }


        }

    }


}
