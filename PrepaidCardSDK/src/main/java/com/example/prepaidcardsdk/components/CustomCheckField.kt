package com.example.prepaidcard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.ColorReset

import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.HitextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCheckField( state:MutableState<Boolean>,text:String,res:Int,onSwitch:()->Unit,) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    OutlinedTextField(
        value = text,
        enabled = false,
        readOnly = true,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
        leadingIcon = {
            Image(
                painter = painterResource(res),
                contentDescription = "first card"
            )
        },
        placeholder = {
            Column(modifier = Modifier.padding(5.dp)) {

                Text(
                    text = text, color = HitextColor,
                    fontFamily = FontFamily(
                        Font(R.font.lato_bold)
                    )
                )
                Text(
                    text = "Activate your Prepaid Card",
                    color = HitextColor,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            focusedBorderColor = Cultured,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Cultured
        ),
        trailingIcon = {

            Switch(
                checked = state.value,
                modifier = Modifier.padding(end=10.dp),
                onCheckedChange = {


                    onSwitch()

                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = ColorReset,
                    uncheckedIconColor = ColorReset,
                    uncheckedBorderColor = ColorReset,
                    disabledUncheckedIconColor = ColorReset
                ),
            )
        }
    )
}