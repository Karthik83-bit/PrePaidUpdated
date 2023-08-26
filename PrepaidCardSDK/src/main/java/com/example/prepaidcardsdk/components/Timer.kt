package com.example.prepaidcardsdk.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

@Composable
fun Timer(timer: MutableState<Int>, showTimer: MutableState<Boolean>) {

    LaunchedEffect(key1 = showTimer.value ){
        while (timer.value>0){
            delay(1000)
            if(timer.value!=0){
                timer.value--
            }
            else if (timer.value == 0){
                showTimer.value = false
            }
        }
    }
}