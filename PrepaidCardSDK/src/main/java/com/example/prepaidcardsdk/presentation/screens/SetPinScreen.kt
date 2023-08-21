package com.example.prepaidcard.screens

//import androidx.compose.material.TextFieldDefaults
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.compose.rememberNavController
//import androidx.compose.material3.TextFieldDefaults
import com.example.prepaidcard.components.CustomButton

import com.example.prepaidcardsdk.presentation.viewmodels.GeneratePinViewModel
import com.example.prepaidcardsdk.ui.theme.cancelGray
import com.example.prepaidcardsdk.ui.theme.lighttealGreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetPinScreen(rootNavController: NavHostController, viewModel: GeneratePinViewModel) {
val pindontMatch=remember{
    mutableStateOf(false)
}
    val context= LocalContext.current
    Scaffold(topBar = {
        CustomTopBar {
            rootNavController.popBackStack()
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(enabled = true, state = ScrollState(0)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (viewModel.isError.value) {
                AlertDialog(onDismissRequest = { }) {
                    Card(Modifier.size(300.dp)) {
                        Box(Modifier.fillMaxSize()) {
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(viewModel.errorMessage.value!!)
                                Button(onClick = {
                                    viewModel.isError.value=false
                                    rootNavController.popBackStack()
                                }) {
                                    Text("ok")

                                }
                            }

                        }

                    }

                }
            }

            else if(viewModel.isLoading.value){
                AlertDialog(onDismissRequest = { }) {
                    Card(Modifier.size(300.dp)) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                           CircularProgressIndicator()

                        }

                    }

                }
            }
//            /CustomTopBar {rootNavController.navigate(Destination.VIEW_CARDS_1)}
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                if(pindontMatch.value){
                    Text("Pins Dont Match",color=Red, fontWeight = FontWeight(700))
                }
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

                BasicTextField(value = viewModel.enterPin.value, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Next), visualTransformation = PasswordVisualTransformation('*') ,onValueChange = {
                    if (it.length <= 4) {
                        viewModel.enterPin.value = it
                    }

                }, modifier = Modifier.fillMaxWidth())
                {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        repeat(4) {
                            val char = when {
                                it >= viewModel.enterPin.value.length -> "0"
                                else -> viewModel.enterPin.value[it]
                            }
                            Box(
                                Modifier
                                    .width(70.dp)
                                    .height(50.dp)
                                    .background(Color.LightGray, shape = RoundedCornerShape(5.dp)), contentAlignment = Alignment.Center) {
                                Text(char.toString())
                            }

                        }
                    }
                }
                Text(
                    text = "Renter Generate PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
                BasicTextField(value = viewModel.reenterPin.value,keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),visualTransformation = PasswordVisualTransformation('*') ,onValueChange = {

                    if (it.length <= 4) {
                        viewModel.reenterPin.value = it
                        if(viewModel.enterPin.value.matches(viewModel.reenterPin.value.toRegex())){
                            pindontMatch.value=false
                        }
                        else{
                            pindontMatch.value=true
                        }
                    }

                }, modifier = Modifier.fillMaxWidth())
                {Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){

                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        repeat(4) {
                            val char = when {
                                it >= viewModel.reenterPin.value.length -> "0"
                                else -> "*"
                            }
                            Box(
                                Modifier
                                    .width(70.dp)
                                    .height(50.dp)
                                    .background(Color.LightGray, shape = RoundedCornerShape(5.dp)), contentAlignment = Alignment.Center) {
                                Text(char.toString())
                            }

                        }
                    }


                }

                }
//                Row {
//                    OutlinedTextField(
//                        value = "", enabled = false, readOnly = true, onValueChange = {},
//                        modifier = Modifier
//                            .width(70.dp)
//                            .padding(5.dp)
//                            .onGloballyPositioned { coordinates ->
//                                textFieldSize = coordinates.size.toSize()
//                            },
//                        placeholder = { Text(text = "") },
//                        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
//                            backgroundColor = Cultured,
//                            focusedBorderColor = Cultured,
//                            unfocusedBorderColor = Color.Transparent,
//                            disabledBorderColor = Cultured
//                        )
//                    )
//                    OutlinedTextField(
//                        value = "", enabled = false, readOnly = true, onValueChange = {},
//                        modifier = Modifier
//                            .width(70.dp)
//                            .padding(5.dp)
//                            .onGloballyPositioned { coordinates ->
//                                textFieldSize = coordinates.size.toSize()
//                            },
//                        placeholder = { Text(text = "") },
//                        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
//                            backgroundColor = Cultured,
//                            focusedBorderColor = Cultured,
//                            unfocusedBorderColor = Color.Transparent,
//                            disabledBorderColor = Cultured
//                        )
//                    )
//                    OutlinedTextField(
//                        value = "", enabled = false, readOnly = true, onValueChange = {},
//                        modifier = Modifier
//                            .width(70.dp)
//                            .padding(5.dp)
//                            .onGloballyPositioned { coordinates ->
//                                textFieldSize = coordinates.size.toSize()
//                            },
//                        placeholder = { Text(text = "") },
//                        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
//                            backgroundColor = Cultured,
//                            focusedBorderColor = Cultured,
//                            unfocusedBorderColor = Color.Transparent,
//                            disabledBorderColor = Cultured
//                        )
//                    )
//                    OutlinedTextField(
//                        value = "", enabled = false, readOnly = true, onValueChange = {},
//                        modifier = Modifier
//                            .width(70.dp)
//                            .padding(5.dp)
//                            .onGloballyPositioned { coordinates ->
//                                textFieldSize = coordinates.size.toSize()
//                            },
//                        placeholder = { Text(text = "") },
//                        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
//                            backgroundColor = Cultured,
//                            focusedBorderColor = Cultured,
//                            unfocusedBorderColor = Color.Transparent,
//                            disabledBorderColor = Cultured
//                        )
//                    )
//                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton(
                    text = "SUBMIT",
                    buttonColor = lighttealGreen,
                    enable=!pindontMatch.value
                ) {
                        rootNavController.navigate(Destination.ENTER_OTP_SCREEN)
                }
                CustomButton(
                    text = "CANCEL",
                    buttonColor = cancelGray,
                    onClick = {},

                )

            }


        }
    }

    }


