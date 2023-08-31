package com.example.prepaidcardsdk.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
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

    val focusManager = LocalFocusManager.current

    val enterBeneisClicked = beneficiaryViewModel.enterBeneisClicked
    val enterBene = beneficiaryViewModel.enterBene
    val enterBeneError = beneficiaryViewModel.enterBeneError
    val enterBeneErrorMessage = beneficiaryViewModel.enterBeneErrorMessage

    val enterBankisClicked = beneficiaryViewModel.enterBankisClicked
    val enterBank = beneficiaryViewModel.enterBank
    val enterBankError = beneficiaryViewModel.enterBankError
    val enterBankErrorMessage = beneficiaryViewModel.enterBankErrorMessage

    val accNumClicked = beneficiaryViewModel.accNumClick
    val accNum = beneficiaryViewModel.accNum
    val accNumError = beneficiaryViewModel.accNumError
    val accNumErrorMessage = beneficiaryViewModel.accNumErrorMessage
    var accNumPattern = Pattern.compile("^[0-9]{9,18}\$")

    var reEnterAccNumClick = beneficiaryViewModel.reEnterAccNumClick
    var reEnterAccNum = beneficiaryViewModel.reEnterAccNum
    var reEnterError = beneficiaryViewModel.reEnterError
    var reEnterErrorMessage = beneficiaryViewModel.reEnterErrorMessage

    val ifscNumClicked = beneficiaryViewModel.ifscNumClick
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
                    if(enterBene.value.isEmpty()){
                        enterBeneError.value = true
                        enterBeneErrorMessage.value = "Please Enter Bene Name"
                    }
                    else if(enterBank.value.isEmpty()){
                        enterBankError.value = true
                        enterBankErrorMessage.value = "Please Enter Bank Name"
                    }
                    else if (accNum.value.isEmpty()) {
                        accNumError.value = true
                        accNumErrorMessage.value = "Please Enter Account Number"
                    }
                    else if(accNum.value.length in 0..8){
                        accNumError.value = true
                        accNumErrorMessage.value = "Please Enter Account Number Correct"
                    }
                    else if (reEnterAccNum.value.isEmpty()){
                        reEnterError.value = true
                        reEnterErrorMessage.value = "Please reEnter the Account Number"
                    }
                    else if (enterIFSC.value.isEmpty()){
                        ifscError.value = true
                        ifscErrorMessage.value = "Please Enter the IFSC Code"
                    }
                    else if (enterBene.value.isNotEmpty() && enterBank.value.isNotEmpty() && accNum.value.isNotEmpty() && accNum.value.length in 9..18 && reEnterAccNum.value.isNotEmpty() && enterIFSC.value.isNotEmpty()) {
                        beneficiaryViewModel.list.add(
                            BeneNameResp(
                                enterBene.value,
                                enterBank.value,
                                accNum.value
                            )
                        )
                        rootnavController.navigate(Destination.SELECT_BENE)
                    }
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
                if (enterBeneisClicked.value) {
                    OutlinedTextField(
                        value = enterBene.value,
                        label = {
                            Text(text = "Enter Beneficiary Name")
                        },
                        onValueChange = {
                            enterBene.value = it
                            if (it.isEmpty()) {
                                focusManager
                                enterBeneError.value = true
                                enterBeneErrorMessage.value = "Beneficiary Name field can't be Empty"
                            }
                            else{
                                enterBeneError.value = false
                            }
                        },
                        trailingIcon = {
                            if (enterBeneError.value)
                                Icon(
                                    painter = painterResource(id = R.drawable.error_icon),
                                    "error",
                                    tint = MaterialTheme.colors.error
                                )
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
                    if (enterBeneError.value) {
                        Text(
                            text = enterBeneErrorMessage.value,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                if(enterBankisClicked.value) {
                    OutlinedTextField(
                        value = enterBank.value,
                        label = { Text(text = "Enter Bank Name") },
                        onValueChange = {
                            enterBank.value = it
                            if (it.isEmpty()) {
                                focusManager
                                enterBankError.value = true
                                enterBankErrorMessage.value =
                                    "Bank Name field can't be Empty"
                            }
                            else{
                                enterBankError.value = false
                            }
                        },
                        trailingIcon = {
                            if (enterBankError.value)
                                Icon(
                                    painter = painterResource(id = R.drawable.error_icon),
                                    "error",
                                    tint = MaterialTheme.colors.error
                                )
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

                    if (enterBankError.value) {
                        Text(
                            text = enterBankErrorMessage.value,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                if(accNumClicked.value) {
                    OutlinedTextField(
                        value = accNum.value,
                        label = { Text(text = "Account number") },
                        onValueChange = {
                            accNum.value = it.filter { char ->
                                char.isDigit()
                            }
                            if (it.isEmpty()) {
                                focusManager
                                accNumError.value = true
                                accNumErrorMessage.value = "Account Number field can't be Empty"
                            }
                            else if (it.length == 9){
                                accNumError.value = false
                            }
                            else if(it.length == 18){
                                focusManager.moveFocus(FocusDirection.Down)
                                accNumError.value = false
                            }
                        },
                        trailingIcon = {
                            if (accNumError.value)
                                androidx.compose.material.Icon(
                                    painter = painterResource(id = R.drawable.error_icon),
                                    "error",
                                    tint = MaterialTheme.colors.error
                                )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        visualTransformation = VisualTransformation.None,
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
                    if (accNumError.value) {
                        Text(
                            text = accNumErrorMessage.value,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                if(reEnterAccNumClick.value) {
                    OutlinedTextField(
                        value = reEnterAccNum.value,
                        label = { Text(text = "Re-Enter Account Number") },
                        onValueChange = {
                            reEnterAccNum.value = it
                            if(!reEnterAccNum.value.matches(accNum.value.toRegex())){
                                focusManager
                                reEnterError.value = true
                                reEnterErrorMessage.value = "Account Number didn't matched"
                            }
                            else if (reEnterAccNum.value.matches(accNum.value.toRegex())){
                                reEnterError.value = false
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        },
                        trailingIcon = {
                            if (reEnterError.value)
                                Icon(
                                    painter = painterResource(id = R.drawable.error_icon),
                                    "error",
                                    tint = MaterialTheme.colors.error
                                )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
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
                    if (reEnterError.value) {
                        Text(
                            text = reEnterErrorMessage.value,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                if(ifscNumClicked.value){
                    OutlinedTextField(
                        value = enterIFSC.value,
                        label = { Text(text = "Enter IFSC Code")},
                        onValueChange = {
                            enterIFSC.value = it
                            if (!enterIFSC.value.matches(ifscPattern.toRegex()) && it.length == 11) {
                                focusManager.moveFocus(FocusDirection.Down)
                                ifscError.value = true
                                ifscErrorMessage.value = "Please Enter Correct IFSC Code"
                            } else if (enterIFSC.value.matches(ifscPattern.toRegex()) && it.length == 11) {
                                focusManager.clearFocus()
                                ifscError.value = false
                            }
                            else if(enterIFSC.value.isEmpty()){
                                ifscError.value = true
                                ifscErrorMessage.value = "IFSC Code can't be Empty"
                            }
                        },
                        trailingIcon = {
                            if (ifscError.value) {
                                Icon(
                                    painter = painterResource(id = R.drawable.error_icon),
                                    "error",
                                    tint = MaterialTheme.colors.error
                                )
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
                    if (ifscError.value) {
                        Text(
                            text = ifscErrorMessage.value,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            Text(modifier = Modifier
                .padding(20.dp)
                .clickable { }, text = "I don't Remember the IFSC code", textDecoration = TextDecoration.Underline, color = tealGreen)

        }

    }
}