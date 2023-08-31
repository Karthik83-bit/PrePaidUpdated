package com.example.prepaidcardsdk.presentation.screens

import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.OutlinedTextField

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.components.CustomAlertDialog
import com.example.prepaidcardsdk.components.CustomLoader
import com.example.prepaidcardsdk.components.PhoneDialer
import com.example.prepaidcardsdk.components.Timer
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.VerifyOTPViewModel
import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.cancelGray
import com.example.prepaidcardsdk.ui.theme.finocolor
import com.example.prepaidcardsdk.utils.PARAMS
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EnterOTPScreen(
    rootNavController: NavHostController,
    viewModel: ManageCardViewModel,
    verifyViewModel: VerifyOTPViewModel,
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var context = LocalContext.current
    var success = remember {
        mutableStateOf(false)
    }
    var scope = rememberCoroutineScope()
    val textlist = listOf(remember {

        mutableStateOf(
            TextFieldValue(
                text = "", selection = TextRange(0)
            )
        )
    }, remember {

        mutableStateOf(
            TextFieldValue(
                text = "", selection = TextRange(0)
            )
        )
    }, remember {

        mutableStateOf(
            TextFieldValue(
                text = "", selection = TextRange(0)
            )
        )
    }, remember {

        mutableStateOf(
            TextFieldValue(
                text = "", selection = TextRange(0)
            )
        )
    }

    )
    val focusRequesterList = listOf<FocusRequester>(
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
    )

    var timer = remember {
        mutableStateOf(SDK_CONSTANTS.expirytime.toInt()*60)
    }
    var showTimer = remember {
        mutableStateOf(true)
    }

    Timer(timer, showTimer)

    if (verifyViewModel.isError.value) {
        AlertDialog(onDismissRequest = { }) {
            CustomAlertDialog(verifyViewModel.errorMessage.value) {
                verifyViewModel.isError.value = false
                if (verifyViewModel.destination.value.isNotEmpty()) {
                    rootNavController.navigate(verifyViewModel.destination.value)
                    verifyViewModel.destination.value = ""

                }


                verifyViewModel.errorMessage.value = ""
                verifyViewModel.destination.value = ""
            }

        }
    }
    if (verifyViewModel.isLoading.value) {
        AlertDialog(onDismissRequest = { /*TODO*/ }) {
            CustomLoader()
        }
    }

    Scaffold(topBar = {
        CustomTopBar {
            rootNavController.popBackStack()
        }
    }) {
        if (viewModel.isError.value) {
            AlertDialog(onDismissRequest = { /*TODO*/ }) {
                CustomAlertDialog(errMsg = viewModel.errorMessage.value) {
                    viewModel.isError.value = false
                    viewModel.errorMessage.value = ""
                    if (viewModel.navDest.value.isNotEmpty()) {
                        rootNavController.navigate(Destination.VIEW_CARDS_SCREEN) {
                            popUpTo(Destination.VIEW_CARDS_SCREEN)
                        }

                    }
                    viewModel.navDest.value = ""
                }
            }

        }
        if (viewModel.isLoading.value) {
            AlertDialog(onDismissRequest = { /*TODO*/ }) {
                CustomLoader()
            }
        }
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(enabled = true, state = ScrollState(0))
        ) {
            val activity = LocalContext.current as Activity
            val keyBoardcontroller = LocalSoftwareKeyboardController.current
//            CustomTopBar {rootNavController.navigate(Destination.PAGE_FOURTY_ONE)}
            Column(
                Modifier.padding(20.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                val animation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.otp))
//                val progress by animateLottieCompositionAsState(composition = animation, iterations = LottieConstants.IterateForever)

//                LottieAnimation(composition = animation, progress = { progress }, modifier = Modifier.size(100.dp) )
                Text(
                    text = "Enter OTP",
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Please enter OTP sent to your mobile number",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )

Box() {

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        repeat(4) {

                            val char = when {
                                it >= viewModel.Otp.value.length -> ""
                                else -> viewModel.Otp.value[it]
                            }


                            Card(
                                Modifier
                                    .padding(2.dp)
                                    .size(50.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                shape = RoundedCornerShape(5.dp),

                                elevation = CardDefaults.cardElevation(10.dp),

                                ) {

                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                                    Text(
                                        char.toString(),
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontWeight = FontWeight(600)
                                    )

                                }
                            }

                        }
                    }

                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp,20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (timer.value == 0) {
                        Text("Resend Otp",
                            fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.clickable {
                                verifyViewModel.VerifyOtp {

                                    if (it.status == "0") {
                                        rootNavController.navigate(Destination.VIEW_CARDS_SCREEN)
                                        verifyViewModel.verifyOtp.value = ""
                                    } else if (verifyViewModel.verifyOtp.value == "") {
                                        verifyViewModel.isError.value = true
                                        verifyViewModel.errorMessage.value = "Otp can't be blank."
                                        verifyViewModel.destination.value = Destination.MPIN_SCREEN
                                    } else {
                                        verifyViewModel.isError.value = true
                                        verifyViewModel.errorMessage.value = it.statusDesc
                                        verifyViewModel.destination.value = Destination.MPIN_SCREEN
                                        verifyViewModel.mobilenum.value = ""
                                        verifyViewModel.verifyOtp.value = ""
                                    }
                                }
                            })
                    } else {
                        Text(
                            "Resend Otp",
                            fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                            fontSize = 16.sp
                        )
                    }

                    if (showTimer.value) {
                        Text(
                            "Remaining time : " + timer.value,
                            fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(0.dp))
            PhoneDialer(otp = viewModel.Otp)
            Column(modifier= Modifier
                .weight(1f)
                .fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(onClick = {
                    textlist.forEach {
                        viewModel.Otp.value = viewModel.Otp.value + it.value.text
                    }
                    if (viewModel.Otp.value.isEmpty() || viewModel.Otp.value.length < 4) {
                        viewModel.isError.value = true
                        viewModel.errorMessage.value = "Enter a valid otp"
                        viewModel.navDest.value = ""
                    }
                    val status = when {

                        SDK_CONSTANTS.isBlock == true -> "unblock"

                        SDK_CONSTANTS.isActive == false -> "active"


                        else -> ""
                    }
                    val params = if (status.equals("Active")) {
                        PARAMS.activate
                    } else {
                        PARAMS.unblock
                    }

                        viewModel.changeCardStatus(viewModel.Otp.value, status = status) {
                            Toast.makeText(context, it.statusDesc, Toast.LENGTH_LONG).show()
                            if (it.status == "0") {
                                success.value = true
                                scope.launch {
                                    delay(2000)
                                    success.value = false
                                }
                                if (status == "active") {
                                    SDK_CONSTANTS.isActive = true
                                } else {
                                    SDK_CONSTANTS.isBlock = false
                                    viewModel.blockCardUI.value = false
                                }



                                if (SDK_CONSTANTS.isActive == true) {
                                    if (SDK_CONSTANTS.isPinSet == true) {
                                        rootNavController.navigate(Destination.GENERATE_PIN_SCREEN) {

                                        }
                                    }

                                    rootNavController.navigate(Destination.CARD_MANAGEMENT_SCREEN) {
                                        this.popUpTo(Destination.VIEW_CARDS_SCREEN)
                                    }
                                } else {
                                    rootNavController.navigate(Destination.CARD_ACTIVATION_SCREEN) {
                                        this.popUpTo(Destination.VIEW_CARDS_SCREEN)
                                    }
                                }

                            } else {
                                viewModel.isError.value = true
                                viewModel.errorMessage.value = it.statusDesc
                                viewModel.navDest.value = Destination.VIEW_CARDS_SCREEN
                            }

                        }

                }, shape = RoundedCornerShape(500.dp), elevation = ButtonDefaults.buttonElevation(20.dp), modifier = Modifier
                    .size(80.dp), colors = ButtonDefaults.buttonColors(Color.White)) {


                    Text(">", fontFamily = FontFamily(Font(R.font.poppins_black)), color = Color.Black, fontSize = 30.sp)

                }
                ElevatedButton(onClick ={
                    rootNavController.navigate(Destination.VIEW_CARDS_SCREEN) {
                        popUpTo(Destination.VIEW_CARDS_SCREEN)
                    }}, shape = RoundedCornerShape(500.dp), elevation = ButtonDefaults.buttonElevation(20.dp), modifier = Modifier
                    .size(80.dp), colors = ButtonDefaults.buttonColors(Color.White)) {


                    Text("X", fontFamily = FontFamily(Font(R.font.poppins_black)), color = Color.Black, fontSize = 30.sp)

                }
//                CustomButton(
//                    text = "SUBMIT",
//                    buttonColor = finocolor,
//                    onClick = {
//                        textlist.forEach {
//                            viewModel.Otp.value = viewModel.Otp.value + it.value.text
//                        }
//                        if (viewModel.Otp.value.isEmpty() || viewModel.Otp.value.length < 4) {
//                            viewModel.isError.value = true
//                            viewModel.errorMessage.value = "Enter a valid otp"
//                            viewModel.navDest.value = ""
//                        }
//                        val status = when {
//
//                            SDK_CONSTANTS.isBlock == true -> "unblock"
//
//                            SDK_CONSTANTS.isActive == false -> "active"
//
//
//                            else -> ""
//                        }
//                        val params = if (status.equals("Active")) {
//                            PARAMS.activate
//                        } else {
//                            PARAMS.unblock
//                        }
//                        if (SDK_CONSTANTS.isPinSet != true) {
//                            viewModel.resetPin(
//                                pin = viewModel.reenterPin.value,
//                                otp = viewModel.Otp.value
//                            ) {
//                                if (it.status == "0") {
//                                    rootNavController.navigate(Destination.CARD_MANAGEMENT_SCREEN)
//                                } else {
//                                    viewModel.isError.value = true
//                                    viewModel.errorMessage.value = it.statusDesc
//                                    viewModel.navDest.value = Destination.VIEW_CARDS_SCREEN
//
//                                }
//                            }
//
//                        } else {
//                            viewModel.changeCardStatus(viewModel.Otp.value, status = status) {
//                                Toast.makeText(context, it.statusDesc, Toast.LENGTH_LONG).show()
//                                if (it.status == "0") {
//                                    success.value = true
//                                    scope.launch {
//                                        delay(2000)
//                                        success.value = false
//                                    }
//                                    if (status == "active") {
//                                        SDK_CONSTANTS.isActive = true
//                                    } else {
//                                        SDK_CONSTANTS.isBlock = false
//                                        viewModel.blockCardUI.value = false
//                                    }
//
//
//
//                                    if (SDK_CONSTANTS.isActive == true) {
//                                        if (SDK_CONSTANTS.isPinSet == true) {
//                                            rootNavController.navigate(Destination.GENERATE_PIN_SCREEN) {
//
//                                            }
//                                        }
//
//                                        rootNavController.navigate(Destination.CARD_MANAGEMENT_SCREEN) {
//                                            this.popUpTo(Destination.VIEW_CARDS_SCREEN)
//                                        }
//                                    } else {
//                                        rootNavController.navigate(Destination.CARD_ACTIVATION_SCREEN) {
//                                            this.popUpTo(Destination.VIEW_CARDS_SCREEN)
//                                        }
//                                    }
//
//                                } else {
//                                    viewModel.isError.value = true
//                                    viewModel.errorMessage.value = it.statusDesc
//                                    viewModel.navDest.value = Destination.VIEW_CARDS_SCREEN
//                                }
//
//                            }
//                        }
//                    },
//
//                    )
//                CustomButton(
//                    text = "CANCEL",
//                    buttonColor = cancelGray,
//                    onClick = {
//                        rootNavController.navigate(Destination.VIEW_CARDS_SCREEN) {
//                            popUpTo(Destination.VIEW_CARDS_SCREEN)
//                        }
//                    },
//
//                    )


            }}
        }
    }


}