package com.example.prepaidcard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.Resetcolor
import com.example.prepaidcardsdk.ui.theme.finocolor
//import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcardsdk.ui.theme.lighttealGreen


@Composable
fun CustomCheckBox(state: MutableState<String>, text:String,res:Int=R.drawable.group_one) {
    Card(colors = CardDefaults.cardColors(Color.White), shape = RoundedCornerShape(2.dp), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 10.dp), modifier = Modifier.padding(10.dp).height(50.dp)){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
        .clickable { state.value = text }
        .fillMaxWidth()){
Row(){
Icon(painter= painterResource(id = res), contentDescription = "", tint = finocolor)
    Spacer(modifier = Modifier.width(5.dp))
    Text(text, fontWeight = FontWeight(500), color = Color.Black)}
        RadioButton(selected = state.value.equals(text), onClick = {state.value=text}, colors = RadioButtonDefaults.colors(
            selectedColor = finocolor,
            unselectedColor = Color.LightGray
        ))
}
}}