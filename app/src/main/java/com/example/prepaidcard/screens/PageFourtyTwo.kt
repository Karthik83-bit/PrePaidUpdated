package com.example.prepaidcard.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.ui.theme.Cultured
import com.example.prepaidcard.ui.theme.cancelGray
import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcard.ui.theme.remainingTimeColor
import com.example.prepaidcard.utils.Destination

@Composable
fun PageFourtyTwo(rootNavController: NavHostController) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        CustomTopBar {rootNavController.navigate(Destination.PAGE_FOURTY_ONE)}
        Column(Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "Enter OTP",
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.lato_bold))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Please enter OTP sent to your mobile number",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
            Row {
                OutlinedTextField(
                    value = "****", enabled = false, readOnly = true, onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    placeholder = { Text(text = "****") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Cultured,
                        focusedBorderColor = Cultured,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Cultured
                    )
                )
            }
            Row {
                Text(
                    text = "0:30s",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = lighttealGreen
                )
                Text(text = "Remaining time", fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = remainingTimeColor
                )
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)){
            CustomButton(text = "SUBMIT",
                buttonColor = lighttealGreen
            ) {rootNavController.navigate(Destination.PAGE_FIFTEEN)}
            CustomButton(text = "CANCEL", buttonColor = cancelGray ) {}
        }
    }

}