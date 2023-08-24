package com.example.prepaidcard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
//import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcardsdk.ui.theme.lighttealGreen


@Composable
fun CustomCheckBox(state: MutableState<String>, text:String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {state.value=text  }){

    RadioButton(selected = state.value.equals(text), onClick = {state.value=text}, colors = RadioButtonDefaults.colors(
        selectedColor = lighttealGreen
    ))
    Text(text, fontWeight = FontWeight(500))
}
}