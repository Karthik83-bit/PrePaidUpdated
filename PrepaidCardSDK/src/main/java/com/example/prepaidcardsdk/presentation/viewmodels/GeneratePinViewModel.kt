package com.example.prepaidcardsdk.presentation.viewmodels

//import android.util.Base64
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.domain.usecases.SetPinUseCase
import com.example.prepaidcardsdk.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

@HiltViewModel
class GeneratePinViewModel @Inject constructor(val setPinUseCase: SetPinUseCase):ViewModel() {
    val enterPin= mutableStateOf("")
    val reenterPin= mutableStateOf("")
    val pinset= mutableStateOf(false)
    val errorState= mutableStateOf(
        CustomError()
    )

    val isLoading= mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.O)
    fun setPin(rootNavController: NavHostController) {
        val key = "ASDFGHJASHJKLQWEASDFGHJASHJKLQWE".toByteArray(StandardCharsets.UTF_8)
        val enc=encryptData(enterPin.value,key)

        viewModelScope.launch {
            setPinUseCase.invoke(encPin=enc).collectLatest {

                when(it){
                    is NetworkResponse.Erroe -> {
                        errorState.value=CustomError(true,it.msg)
                    }
                    is NetworkResponse.Loading -> {
                        isLoading.value=true
                    }
                    is NetworkResponse.Success -> {
                        isLoading.value=false
                        pinset.value=true
                        rootNavController.navigate(Destination.ENTER_OTP_SCREEN)
                    }
                }
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun encryptData(data: String, key: ByteArray): String
    {
        println("Before Encrypt")
        println(data)
        val dataToSend = data.toByteArray(StandardCharsets.UTF_8)
        println(dataToSend)
        println(String( dataToSend))
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



data class CustomError(val isError: Boolean?=false, val errStr: String?="")
}
