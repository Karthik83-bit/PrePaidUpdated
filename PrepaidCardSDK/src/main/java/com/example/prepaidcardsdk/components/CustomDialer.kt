package com.example.prepaidcardsdk.components

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PhoneDialer(otp:MutableState<String>) {
    var phoneNumber by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val numberPad = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "x")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(250.dp).width(500.dp)   ) {





        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(8.dp)
        ) {
            items(numberPad) { number ->

                NumberButton(
                    number =  if(number.equals("x")){"⌫"}else number,
                    onClick = {
                        if(number.equals("x")){
                            if (otp.value.isNotEmpty()) {
                                otp.value = otp.value.dropLast(1)
                            }
                        }else{
                            if(otp.value.length<10){
                        otp.value += number}}
                    }
                )
            }
        }


    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NumberButton(modifier:Modifier=Modifier,number: String, onClick: () -> Unit) {
    val context= LocalContext.current
    val vibrator = ContextCompat.getSystemService(context,Vibrator::class.java)
    Box(
        modifier = modifier
            .size(60.dp)
            .padding(4.dp)
            .clickable {
                val effect = VibrationEffect.createOneShot(100, VibrationEffect.EFFECT_HEAVY_CLICK)
                vibrator?.vibrate(effect)
                onClick() },
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = number,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier.testTag(number)
            )

        }
    )
}