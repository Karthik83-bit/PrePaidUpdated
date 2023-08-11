package com.example.prepaidcard.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.prepaidcard.ui.theme.lighttealGreen


@Composable
fun CustomCheckBox(state: MutableState<String>, text:String) {
    Row(verticalAlignment = Alignment.CenterVertically){

    RadioButton(selected = state.value.equals(text), onClick = {state.value=text}, colors = RadioButtonDefaults.colors(
        selectedColor = lighttealGreen
    ))
    Text(text, fontWeight = FontWeight(500))
}
}