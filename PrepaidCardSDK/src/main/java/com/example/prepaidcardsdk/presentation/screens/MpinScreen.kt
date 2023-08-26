package com.example.prepaidcard.screens

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
//import com.example.prepaidcard.R
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.components.CustomAlertDialog
import com.example.prepaidcardsdk.components.CustomLoader
import com.example.prepaidcardsdk.components.CustomOTPinp
import com.example.prepaidcardsdk.components.OTPInput
import com.example.prepaidcardsdk.components.Timer
import com.example.prepaidcardsdk.presentation.viewmodels.VerifyOTPViewModel
import com.example.prepaidcardsdk.ui.theme.finocolor

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MpinScreen(rootNavController: NavHostController, viewModel: VerifyOTPViewModel) {

    val otpValue= remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    val db = ContextCompat.getDrawable(context, R.drawable.enterotp)

// in below line we are creating our bitmap and initializing it.
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val textlist=listOf(
        remember {

            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange(0)
                )
            )},
        remember {

            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange(0)
                )
            )},
        remember {

            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange(0)
                )
            )},
        remember {

            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange(0)
                )
            )}

    )
    val focusRequesterList= listOf<FocusRequester>(
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
    )
    var timer = remember {
        mutableStateOf(5)
    }
    var showTimer = remember {
        mutableStateOf(true)
    }

    Timer(timer, showTimer)


    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(enabled = true, state = ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.enterotp),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp)
                        .rotate(25f)
                )
                Image(painter = painterResource(id = R.drawable.key),
                    contentDescription = "",
                    modifier = Modifier

                        .size(100.dp)
                        .drawBehind {


                        })
                if (viewModel.isError.value) {
                    AlertDialog(onDismissRequest = { }) {
                        CustomAlertDialog(viewModel.errorMessage.value){
                            viewModel.isError.value = false
                            if(viewModel.destination.value.isNotEmpty()){
                                rootNavController.navigate(viewModel.destination.value)
                                viewModel.destination.value=""

                            }


                            viewModel.errorMessage.value = ""
                            viewModel.destination.value = ""
                        }
//                        Card(Modifier.size(300.dp)) {
//                            Box(Modifier.fillMaxSize()) {
//                                Column(
//                                    Modifier.fillMaxSize(),
//                                    verticalArrangement = Arrangement.SpaceBetween,
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Row {
//                                        Box(Modifier.fillMaxWidth(0.8f))
//                                        IconButton(onClick = {
//                                            viewModel.isError.value = false
//                                            viewModel.isError.value = false
//                                        }) {
//                                            Icon(
//                                                painterResource(id = R.drawable.baseline_close_24),
//                                                ""
//                                            )
//                                        }
//                                    }
//                                    Column(
//                                        modifier = Modifier.fillMaxSize(),
//                                        verticalArrangement = Arrangement.Center,
//                                        horizontalAlignment = Alignment.CenterHorizontally
//                                    ) {
//
//                                        Text(
//                                            viewModel.errorMessage.value.replaceFirstChar {
//                                                it.uppercase()
//                                            },
//                                            fontWeight = FontWeight(400),
//                                            style = TextStyle(
//                                                fontSize = 20.sp
//
//                                            ),
//                                        )
//                                        Button(onClick = {
//                                            viewModel.isError.value = false
//
//                                            rootNavController.navigate(viewModel.destination.value)
//                                            viewModel.errorMessage.value = ""
//                                            viewModel.destination.value = ""
//                                        }) {
//                                            Text("ok")
//
//                                        }
//                                    }
//                                }
//
//                            }
//
//                        }

                    }
                }
                if(viewModel.isLoading.value){
                    AlertDialog(onDismissRequest = { /*TODO*/ }) {
                        CustomLoader()
                    }
                }

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    "Verify OTP",
                    fontSize = (18.dp).value.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_bold)))
                )
                Text("MPIN sent to your registered mobile number.", fontSize = 14.sp)
            }

            val cont = LocalContext.current
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                OTPInput(textList = textlist, requestList = focusRequesterList)
//                BasicTextField(
//                    value = viewModel.verifyOtp.value, onValueChange = {
//                        if (it.length <= 4) {
//                            viewModel.verifyOtp.value = it
//
//
//                    }
//
//
//                    }, keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.NumberPassword,
//                        imeAction = ImeAction.Done
//                    )
//                ) {
////                    OTPInput(textList =textlist , requestList =focusRequesterList )
//                    Row {
//                        repeat(4) {
//                            val char = when {
//                                it >= viewModel.verifyOtp.value.length -> "0"
//                                else -> viewModel.verifyOtp.value[it]
//                            }
//                            Column(
//                                Modifier
//                                    .width(60.dp)
//
//                                    .padding(horizontal = 10.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.spacedBy(6.dp)
//                            ) {
//
//                                Text(
//                                    char.toString(),
//                                    color = if (it >= viewModel.verifyOtp.value.length) Color.Gray else Color.Black,
//                                    fontWeight = if (it >= viewModel.verifyOtp.value.length) FontWeight(
//                                        300
//                                    ) else FontWeight(800)
//                                )
//
//
//                                Row(
//                                    Modifier
//                                        .height(2.dp)
//                                        .fillMaxWidth()
//                                        .background(Color.Gray)
//                                ) {}
//
//
//                            }
//                        }
//                    }
//
//
//                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (timer.value == 0){
                        Text(
                            "Resend Otp",
                            fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.clickable { viewModel.VerifyOtp {

                                if (it.status == "0") {
                                    rootNavController.navigate(Destination.VIEW_CARDS_SCREEN)
                                    viewModel.verifyOtp.value=""
                                }
                                else if(viewModel.verifyOtp.value == "") {
                                    viewModel.isError.value =true
                                    viewModel.errorMessage.value ="Otp can't be blank."
                                    viewModel.destination.value = Destination.MPIN_SCREEN
                                }else
                                {

                                    viewModel.isError.value = true
                                    viewModel.errorMessage.value = it.statusDesc
                                    viewModel.destination.value = Destination.MPIN_SCREEN
                                    viewModel. mobilenum.value=""
                                    viewModel.verifyOtp.value=""
                                }
                            } }
                        )
                    }
                    else {
                        Text(
                            "Resend Otp",
                            fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                            fontSize = 14.sp
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
            ElevatedButton(
                onClick = {
                    textlist.forEach {
                        viewModel.verifyOtp.value=viewModel.verifyOtp.value+it.value.text
                    }
                    viewModel.VerifyOtp {

                        if (it.status == "0") {
                            rootNavController.navigate(Destination.VIEW_CARDS_SCREEN)
                            viewModel.verifyOtp.value=""
                        }
                        else if(viewModel.verifyOtp.value == "") {
                            viewModel.isError.value =true
                            viewModel.errorMessage.value ="Otp can't be blank."
                            viewModel.destination.value = Destination.MPIN_SCREEN
                        }else
                        {

                            viewModel.isError.value = true
                            viewModel.errorMessage.value = it.statusDesc
                            viewModel.destination.value = Destination.MPIN_SCREEN
                            viewModel. mobilenum.value=""
                            viewModel.verifyOtp.value=""
                        }
                    }

                },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.buttonElevation(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff32DBDE))
            ) {
                Text("VERIFY OTP", fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))))
            }
        }



    }
}