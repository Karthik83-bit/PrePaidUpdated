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

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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

import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
//import com.example.prepaidcard.ui.theme.Cultured
//import com.example.prepaidcard.ui.theme.cancelGray
//import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.cancelGray
import com.example.prepaidcardsdk.ui.theme.lighttealGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageFourtyOne(rootNavController: NavHostController) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Scaffold(topBar = { CustomTopBar {
        rootNavController.popBackStack()
    }}) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
//            /CustomTopBar {rootNavController.navigate(Destination.VIEW_CARDS_1)}
            Column(Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Generate Pin",
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Enter Generate PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
                Row {
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                }
                Text(
                    text = "Renter Generate PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
                Row {
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)){
                CustomButton(text = "SUBMIT",
                    buttonColor = lighttealGreen
                ) {rootNavController.navigate(Destination.ENTER_OTP_SCREEN)}
                CustomButton(text = "CANCEL", buttonColor = cancelGray ) {}
            }
        }
    }

}