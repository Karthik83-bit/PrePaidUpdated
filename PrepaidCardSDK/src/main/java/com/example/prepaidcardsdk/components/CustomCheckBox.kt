package com.example.prepaidcard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.Resetcolor
import com.example.prepaidcardsdk.ui.theme.cdback
import com.example.prepaidcardsdk.ui.theme.finocolor
//import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcardsdk.ui.theme.lighttealGreen


@Composable
fun CustomCheckBox(state: MutableState<String>, text:String,res:Int=R.drawable.group_one) {
    Card(colors = CardDefaults.cardColors(finocolor), shape = RoundedCornerShape(2.dp), border = BorderStroke(2.dp, color = White),elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 10.dp), modifier = Modifier.padding(10.dp).height(60.dp)){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
        .clickable { state.value = text }
        .fillMaxWidth().height(70.dp)){
Row(Modifier.padding(horizontal = 10.dp)){
Icon(painter= painterResource(id = res), contentDescription = "", tint = Color.White)
    Spacer(modifier = Modifier.width(15.dp))
    Text(text, fontWeight = FontWeight(500), color = White, fontFamily = FontFamily(Font(R.font.poppins_regular)))}
        RadioButton(selected = state.value.equals(text), onClick = {state.value=text}, colors = RadioButtonDefaults.colors(
            selectedColor = Color.White,

            unselectedColor = Color.White

        ))
}
}
}