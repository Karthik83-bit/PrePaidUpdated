package com.example.prepaidcardsdk.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomOTPinp() {
    val enterpin= remember { mutableStateOf("") }
    Row(){
        repeat(4){

            TextField(value = if(it>=enterpin.value.length)"0" else enterpin.value[it].toString(), onValueChange = { ca->
                if(it>=enterpin.value.length)enterpin.value="0" else if(it>=enterpin.value.length)"0" else enterpin.value=enterpin.value.replaceRange(it-1,it,ca)}, modifier = Modifier.width(70.dp))
        }

    }
}