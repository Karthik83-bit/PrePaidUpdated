package com.prepaid_service_app.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.data.model.resp.BeneNameResp
import com.example.prepaidcardsdk.presentation.viewmodels.BeneficiaryViewModel
import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.tealGreen
import java.util.regex.Pattern

@Composable
fun AddBene(rootnavController: NavHostController, beneficiaryViewModel: BeneficiaryViewModel){
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val enterBene = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val enterBank = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val accNumClicked = beneficiaryViewModel.accNumClick
    val accNum = beneficiaryViewModel.accNum
    val accNumError = beneficiaryViewModel.accNumError
    val accNumErrorMessage = beneficiaryViewModel.accNumErrorMessage
    var accNumPattern = Pattern.compile("^[0-9]{9,18}\$")

    val reEnterAccNum = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val enterIFSC = beneficiaryViewModel.ifscNum
    val ifscError = beneficiaryViewModel.ifscError
    val ifscErrorMessage = beneficiaryViewModel.ifscErrorMessage
    var ifscPattern = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}\$")



    Scaffold (topBar = {
        CustomTopBar {
            rootnavController.popBackStack()
        }
    },
        bottomBar = {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                CustomButton(text = "Add Beneficiary", buttonColor = tealGreen) {
                    beneficiaryViewModel.list.add(BeneNameResp(enterBene.value.text, enterBank.value.text, accNum.value))
                    rootnavController.navigate(Destination.SELECT_BENE)
                }
            }
        }
    ){
        Column (Modifier.padding(it)){
            Row(
                Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.account_circle),
                    contentDescription = "New Beneficiary",
                    tint = tealGreen
                )
                Text(
                    text = "Add New Beneficiary",
                    fontFamily = FontFamily(Font(R.font.poppins_black)),
                    color = tealGreen
                )
            }
            Column (verticalArrangement = Arrangement.SpaceEvenly) {
                OutlinedTextField(
                    value = enterBene.value,
                    label = {
                            Text(text = "Enter Beneficiary Name")
                    },
                    onValueChange = {
                        enterBene.value = it
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
                    value = enterBank.value,
                    label = { Text(text = "Enter Bank Name")},
                    onValueChange = {
                        enterBank.value = it
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
                if(accNum.value != "" && accNum.value.matches(accNumPattern.toRegex())){
                    accNumError.value = true
                    accNumErrorMessage.value = "Please Enter Correct Account Number"
                }
                OutlinedTextField(
                    value = accNum.value,
                    label = { Text(text = "Account number")},
                    onValueChange = {
                        accNum.value = it
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
                    value = reEnterAccNum.value,
                    label = { Text(text = "Re-Enter Account Number")},
                    onValueChange = {
                        reEnterAccNum.value = it
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
                    value = enterIFSC.value,
                    label = { Text(text = "Enter IFSC Code")},
                    onValueChange = {
                        enterIFSC.value = it
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
            Text(modifier = Modifier
                .padding(20.dp)
                .clickable { }, text = "I don't Remember the IFSC code", textDecoration = TextDecoration.Underline, color = tealGreen)

        }

    }
}