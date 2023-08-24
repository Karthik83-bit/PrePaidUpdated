package com.example.prepaidcardsdk.presentation.screens

import android.text.style.UnderlineSpan
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.tealGreen

@Composable
fun AddBene(rootnavController: NavHostController){
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Scaffold (topBar = {
        CustomTopBar {
            rootnavController.popBackStack()
        }
    },
        bottomBar = {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                CustomButton(text = "Add Beneficiary", buttonColor = tealGreen) {
                    rootnavController.navigate(Destination.SELECT_BENE)
                }
            }
        }
    ){
        Column (Modifier.padding(it)){
            Row (Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Icon(painter = painterResource(id = R.drawable.add_person), contentDescription = "Select Beneficiary", tint = tealGreen)
                Text(text = "Select Beneficiary", fontFamily = FontFamily(Font(R.font.poppins_black)), color = tealGreen)
            }
            Column (verticalArrangement = Arrangement.SpaceEvenly) {
                OutlinedTextField(
                    value = "Enter Beneficiary Name",
                    enabled = true,
                    readOnly = false,
                    onValueChange = {
                        if (it.length <= 4) {
                            it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Cultured,
                        focusedBorderColor = Cultured,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Cultured
                    )
                )
                OutlinedTextField(
                    value = "Enter Bank Name",
                    enabled = true,
                    readOnly = false,
                    onValueChange = {
                        if (it.length <= 4) {
                            it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Cultured,
                        focusedBorderColor = Cultured,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Cultured
                    )
                )
                OutlinedTextField(
                    value = "Account number",
                    enabled = true,
                    readOnly = false,
                    onValueChange = {
                        if (it.length <= 4) {
                            it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Cultured,
                        focusedBorderColor = Cultured,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Cultured
                    )
                )
                OutlinedTextField(
                    value = "Re-Enter Account Number",
                    enabled = true,
                    readOnly = false,
                    onValueChange = {
                        if (it.length <= 4) {
                            it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Cultured,
                        focusedBorderColor = Cultured,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Cultured
                    )
                )
                OutlinedTextField(
                    value = "Enter IFSC Code",
                    enabled = true,
                    readOnly = false,
                    onValueChange = {
                        if (it.length <= 4) {
                            it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Cultured,
                        focusedBorderColor = Cultured,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Cultured
                    )
                )
            }
            Text(modifier = Modifier.padding(20.dp).clickable {  }, text = "I don't Remember the IFSC code", textDecoration = TextDecoration.Underline, color = tealGreen)

        }

    }
}