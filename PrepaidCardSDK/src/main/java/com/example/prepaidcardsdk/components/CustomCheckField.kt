package com.example.prepaidcard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.Resetcolor

import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.HitextColor
import com.example.prepaidcardsdk.ui.theme.cdback
import com.example.prepaidcardsdk.ui.theme.finocolor
import com.example.prepaidcardsdk.ui.theme.light_finocolor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCheckField( state:MutableState<Boolean>,text:String,res:Int,onSwitch:()->Unit,) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Card(colors = CardDefaults.cardColors(finocolor), shape = RoundedCornerShape(2.dp), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 10.dp), modifier = Modifier.padding(10.dp).height(60.dp)){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
            .clickable {  }
            .fillMaxWidth().height(70.dp)){
            Row(Modifier.padding(horizontal = 10.dp)){
                Icon(painter= painterResource(id = res), contentDescription = "",tint= Color.White)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text, fontWeight = FontWeight(500), color = Color.White, fontFamily = FontFamily(Font(R.font.poppins_regular)))}
//            RadioButton(selected = state.value.equals(text), onClick = {state.value=text}, colors = RadioButtonDefaults.colors(
//                selectedColor = finocolor,
//                unselectedColor = Color.LightGray
//            ))

            Switch(
                checked = state.value,
                modifier = Modifier.padding(end=10.dp).size(50.dp),
                onCheckedChange = {


                    onSwitch()

                },
                thumbContent = {
                    Box(Modifier.height(40.dp).width(70.dp))
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = light_finocolor,
                    uncheckedIconColor = light_finocolor,
                    uncheckedThumbColor = light_finocolor,
                    disabledUncheckedThumbColor = light_finocolor,
                    checkedThumbColor = Color.White,

                    disabledUncheckedIconColor = light_finocolor,
                    checkedIconColor = Color.White,
                    uncheckedBorderColor = Color.LightGray.copy(0.5f),
                    disabledCheckedBorderColor = Color.Transparent,
                    checkedBorderColor =Color.Transparent ,
                    disabledUncheckedBorderColor = Color.Transparent,
                    uncheckedTrackColor = Color.White.copy(0.5f)


                ),
            )
        }
    }
}
//    OutlinedTextField(
//        value = text,
//        enabled = false,
//        readOnly = true,
//        onValueChange = {},
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 10.dp, vertical = 10.dp)
//            .onGloballyPositioned { coordinates ->
//                textFieldSize = coordinates.size.toSize()
//            },
//        leadingIcon = {
//            Image(
//                painter = painterResource(res),
//                contentDescription = "first card"
//            )
//        },
//        placeholder = {
//            Column(modifier = Modifier.padding(0.dp)) {
//
//                Text(
//                    text = text, color = HitextColor,
//                    fontFamily = FontFamily(
//                        Font(R.font.poppins_regular)
//                    )
//                )
//                Text(
//                    text = "Activate your Prepaid Card",
//                    color = HitextColor,
//                    fontFamily = FontFamily(Font(R.font.lato_regular))
//                )
//            }
//        },
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            containerColor = Color.White,
//            focusedBorderColor = Cultured,
//            unfocusedBorderColor = Color.Transparent,
//            disabledBorderColor = Cultured,
//            disabledTextColor = finocolor
//        ),
//        trailingIcon = {
//
//        }
//    )
