package com.example.prepaidcardsdk.presentation.screens

//import com.example.prepaidcard.R
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.prepaidcard.components.CustomCheckBox
import com.example.prepaidcard.components.CustomSheetWrap
import com.example.prepaidcard.components.EnterOTPPinSheet
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.components.CustomAlertDialog
import com.example.prepaidcardsdk.components.CustomLoader
import com.example.prepaidcardsdk.components.PhoneDialer
import com.example.prepaidcardsdk.data.model.req.VerifyOtpReq
import com.example.prepaidcardsdk.presentation.viewmodels.VerifyOTPViewModel
import com.example.prepaidcardsdk.ui.theme.finocolor
import com.example.prepaidcardsdk.ui.theme.light_finocolor
import com.example.prepaidcardsdk.ui.theme.tealGreen
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterMobileNumScreen(rootNavController: NavHostController, viewModel: VerifyOTPViewModel) {
/*
    val mobilenumValue= remember {
        mutableStateOf("")
    }*/
    val context= LocalContext.current
//    val db = ContextCompat.getDrawable(context, R.drawable.enterotp)

    // in below line we are creating our bitmap and initializing it.
//    val bit = Bitmap.createBitmap(
//        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
//    )
    val showNum= remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 =true , ){
        showNum.value=true

    }
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


    val timer = remember {
        mutableStateOf(5)
    }
    val showTimer = remember {
        mutableStateOf(false)
    }

    if(viewModel.isLoading.value){
        AlertDialog(onDismissRequest = { /*TODO*/ }) {
            CustomLoader()
        }
    }
    if(viewModel.verifyOTPScaffoldState.value){
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.2f)))
    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White)
            .blur(if (viewModel.verifyOTPScaffoldState.value) 10.dp else 0.dp)
            .verticalScroll(enabled = true, state = ScrollState(0)), horizontalAlignment = Alignment.CenterHorizontally)
    {

        if(showNum.value){val checkState= remember {
            mutableStateOf(false)
        }

            AlertDialog(onDismissRequest = { /*TODO*/ }) {
                Card(
                    Modifier
                        .height(140.dp)
                        .fillMaxWidth(0.7f)) {
                    Row(modifier = Modifier.fillMaxHeight(0.3f), verticalAlignment = Alignment.CenterVertically){
                        Checkbox(
                            checked = checkState.value,
                            onCheckedChange ={
                            checkState.value=true
                            viewModel.mobilenum.value="9127866307"
                            showNum.value=false
                        }
                        )
                        Text("9127866307")
                    }
                    Row(modifier = Modifier.fillMaxHeight(0.3f), verticalAlignment = Alignment.CenterVertically){
                        Checkbox(
                            checked = checkState.value,
                            onCheckedChange ={
                                checkState.value=true
                                viewModel.mobilenum.value="9128663078"
                                showNum.value=false
                            }
                        )
                        Text("9128663078")
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Button(onClick = { showNum.value=false }, shape = RoundedCornerShape(3.dp), colors = ButtonDefaults.buttonColors(
                        finocolor)) {
                        Text("Manually input number")
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(verticalArrangement = Arrangement.spacedBy(50.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Box(contentAlignment = Alignment.Center){
                Image(painter = painterResource(id = R.drawable.login_bg) , contentDescription ="", modifier = Modifier
                    .size(100.dp))

            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text("Mobile Number", fontSize = (18.dp).value.sp, fontFamily = FontFamily(listOf(Font(R.font.roboto_bold))))
                Text("Enter a valid mobile number.", fontSize = 14.sp)
            }

            val cont= LocalContext.current
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
//                BasicTextField(value = viewModel.mobilenum.value, onValueChange = {
//                        if(it.length<=10){
//                            viewModel.mobilenum.value=it
//                        }
//
//
//
//
//
//                }, keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.NumberPassword,
//                    imeAction = ImeAction.Done
//                )
//                )
//
           Box  {
//                   OTPInput(textList =textlist , requestList =focusRequesterList )
                    Row(){
                        repeat(10){
                            val char=when{
                                it>=viewModel.mobilenum.value.length->"0"
                                else->viewModel.mobilenum.value[it]
                            }
                            Column(
                                Modifier
                                    .width(35.dp)
                                    .padding(horizontal = 10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(6.dp)) {

                                Text(char.toString(), color =if(it>=viewModel.mobilenum.value.length) Color.Gray else Color.Black, fontWeight = if(it>=viewModel.mobilenum.value.length) FontWeight(300) else FontWeight(800))


                                Row(
                                    Modifier
                                        .height(2.dp)
                                        .fillMaxWidth()
                                        .background(Color.Gray)){}


                            }
                        }
                    }
               Spacer(modifier = Modifier.height(100.dp))
                    if(viewModel.isError.value){
                        AlertDialog(onDismissRequest = { }) {
                            CustomAlertDialog(errMsg = viewModel.errorMessage.value) {
                                viewModel.isError.value=false
                                viewModel.verifyOTPScaffoldState.value=false
                            }
//                            Card(Modifier.size(300.dp))
//                            {
//                                Box(Modifier.fillMaxSize()) {
//                                    Column(
//                                        Modifier.fillMaxSize(),
//                                        verticalArrangement = Arrangement.SpaceBetween,
//                                        horizontalAlignment = Alignment.CenterHorizontally
//                                    ) {
//                                        Row(){
//                                            Box(Modifier.fillMaxWidth(0.8f))
//                                            IconButton(onClick = {
//                                                viewModel.isError.value = false
//                                                viewModel.isError.value = false
//                                            }) {
//                                                Icon(painterResource(id = R.drawable.baseline_close_24), "")
//                                            }
//                                        }
//                                        Column(modifier = Modifier.fillMaxSize(),
//                                            verticalArrangement = Arrangement.Center,
//                                            horizontalAlignment = Alignment.CenterHorizontally) {
//
//                                            Text(viewModel.errorMessage.value.replaceFirstChar {
//                                                it.uppercase()
//                                            }, fontWeight = FontWeight(400), style = TextStyle(
//                                                fontSize = 20.sp
//
//                                            ),)
//                                            Button(onClick = {
//                                                viewModel.isError.value = false
//
//                                                rootNavController.navigate(viewModel.destination.value)
//                                                viewModel.errorMessage.value = ""
//                                                viewModel.destination.value = ""
//                                            }) {
//                                                Text("ok")
//
//                                            }
//                                        }
//                                    }
//
//                                }
//
//                            }

                        }
                    }


                }

            }
            val width= animateDpAsState(targetValue = if(viewModel.isLoading.value) 100.dp else 400.dp)
            val height= animateDpAsState(targetValue = if(viewModel.isLoading.value) 100.dp else 50.dp)



        }

        PhoneDialer(viewModel.mobilenum)
        ElevatedButton(onClick = {
            viewModel.sendOtp(params = "CARD_OTP") {
                if(it.status == "0"){
                    viewModel.verifyOTPScaffoldState.value=true
                    Toast.makeText(context, "OTP sent to the mobile number.", Toast.LENGTH_SHORT).show()
                }
                else if (viewModel.mobilenum.value == "") {
                    viewModel.isError.value = true
                    viewModel.errorMessage.value = "Mobile Number field can't be blank."
                    viewModel.destination.value = Destination.ENTER_MOBILE_NUM_SCREEN
                } else if (viewModel.mobilenum.value.length <= 9) {
                    viewModel.isError.value = true
                    viewModel.errorMessage.value = "Enter a valid Mobile Number."
                    viewModel.destination.value = Destination.ENTER_MOBILE_NUM_SCREEN

                }
                else{
                    viewModel.isError.value = true
                    viewModel.errorMessage.value=it.statusDesc
                }

            }

        }, shape = RoundedCornerShape(500.dp), elevation = ButtonDefaults.buttonElevation(20.dp), modifier = Modifier
            .size(80.dp), colors = ButtonDefaults.buttonColors(Color.White)) {


            Text(">", fontFamily = FontFamily(Font(R.font.poppins_black)), color = Color.Black, fontSize = 30.sp)

        }



    }
    CustomSheetWrap(state = viewModel.verifyOTPScaffoldState, color = light_finocolor) {
        EnterOTPPinSheet(state = viewModel.verifyOtp, verifyViewModel = viewModel, oncancel = { viewModel.verifyOTPScaffoldState.value=false}) {
            viewModel.VerifyOtp {
                if(it.status=="0"){
                    rootNavController.navigate(Destination.VIEW_CARDS_SCREEN){
                        popUpTo(Destination.ENTER_MOBILE_NUM_SCREEN)
                    }
                    viewModel.verifyOTPScaffoldState.value=false
                }
                else{
                    viewModel.isError.value=true
                    viewModel.errorMessage.value=it.statusDesc
                }
            }

        }

    }



}