package com.example.prepaidcard.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newui.components.CardFace
import com.example.newui.components.FlipCard

import com.example.prepaidcard.components.CustomCheckBox
import com.example.prepaidcard.components.CustomCheckField
import com.example.prepaidcard.components.CustomSheetWrap

import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.components.Sheet
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcard.utils.FilterOption
import com.example.prepaidcard.utils.STRING
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.components.CustomAlertDialog
import com.example.prepaidcardsdk.components.CustomLoader
import com.example.prepaidcardsdk.components.CustomSheetAlertDialog
import com.example.prepaidcardsdk.components.Timer
import com.example.prepaidcardsdk.presentation.viewmodels.CardDataViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.GeneratePinViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.VerifyOTPViewModel
import com.example.prepaidcardsdk.ui.theme.Resetcolor
import com.example.prepaidcardsdk.ui.theme.cdback
import com.example.prepaidcardsdk.ui.theme.finocolor
import com.example.prepaidcardsdk.ui.theme.light_finocolor
import com.example.prepaidcardsdk.utils.PARAMS
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardManagementScreen(
    rootNavController: NavHostController,
    viewModel: GeneratePinViewModel,
    cardDataViewModel: CardDataViewModel,
    onClick: (state: Boolean) -> Unit = {},
    manageViewModel: ManageCardViewModel,
    verifyViewModel: VerifyOTPViewModel,
) {
    val scope = rememberCoroutineScope()
    val sucess = remember {
        mutableStateOf(false)
    }
    val sucessMsg = remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = sucess.value) {
        scope.launch {
            delay(2000)
            sucess.value = false
        }
    }
    LaunchedEffect(key1 = true) {
        if (manageViewModel.startanim.value == false) {
            manageViewModel.startanim.value = !manageViewModel.startanim.value
        }
    }

    if (verifyViewModel.isError.value) {
        AlertDialog(onDismissRequest = { }) {
            CustomAlertDialog(verifyViewModel.errorMessage.value) {
                verifyViewModel.isError.value = false
                if (verifyViewModel.destination.value.isNotEmpty()) {
                    rootNavController.navigate(verifyViewModel.destination.value)
                    verifyViewModel.destination.value = ""

                }
                verifyViewModel.verifyOtp.value=""
                cardDataViewModel.otp.value=""
                viewModel.otp.value=""


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


//    val ReplaceToggleState = manageViewModel.ReplaceToggleState
//
//
//    val CvvToggleState = manageViewModel.CvvToggleState_d
//
//    val ResetPinToggleState = manageViewModel.ResetPinToggleState_d
//
//    val PauseCardToggleState = manageViewModel.PauseCardToggleState_d
//
//    val PauseCardOtpToggleState = manageViewModel.PauseCardOtpState
//
//    val HotListToggleState = manageViewModel.HotListToggleState_d
//
//    val ResetPinOtpState = manageViewModel.ResetPinOtpState
//
//    val DetailsState = manageViewModel.DetailsState
    val context = LocalContext.current

    @Composable
    fun ResetPinSheet(
        hotlist: MutableState<Boolean>,
        color: Color = Color.White,
        onSubmit: () -> Unit,
    ) {
        var isError = remember {
            mutableStateOf(false)
        }
        var errStr = remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (isError.value) {
                    Text(errStr.value, color = Color.Red, fontWeight = FontWeight(700))
                }
                Text(
                    text = "Generate Pin",
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Enter New PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = Color.White
                )
                val intSrc = remember {
                    MutableInteractionSource()
                }
//                     OutlinedTextField(value = genPin.value, onValueChange = {genPin.value=it}, colors = OutlinedTextFieldDefaults.colors(disabledBorderColor =Color.Transparent ))
//                BasicTextField(value =genPin.value , modifier = Modifier.fillMaxWidth().height(40.dp), enabled = true, interactionSource = intSrc, onValueChange = {genPin.value=it}){
//                    TextFieldDefaults.TextFieldDecorationBox(
//                        value =genPin.value,
//                        innerTextField = { it },
//                        enabled =true ,
//                        singleLine = true,
//                        visualTransformation = VisualTransformation.None ,
//                        interactionSource =intSrc,
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Gray,
//                            textColor = Color.Black
//                        ),
//
//
//                    )
//                }
                TextField(
                    value = manageViewModel.enterPin.value,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Enter PIN") },
                    onValueChange = {
                        manageViewModel.enterPin.value = it
                    },
                    shape = RoundedCornerShape(2.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.textFieldColors(

                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    )
                )
                Text(
                    text = "Re-nter New PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = Color.White
                )
                TextField(
                    value = manageViewModel.reenterPin.value,

                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Enter PIN", modifier = Modifier.padding(0.dp)) },
                    onValueChange = {
                        manageViewModel.reenterPin.value = it
                        if (it.length == 4 && !it.matches(manageViewModel.enterPin.value.toRegex())) {
                            isError.value = true
                            errStr.value = "PINS DONT MATCH"
                        } else {
                            isError.value = false
                        }

                    },
                    shape = RoundedCornerShape(2.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.textFieldColors(

                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    )
                )
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Button(
                    onClick = {
                        if (manageViewModel.enterPin.value.isEmpty() || manageViewModel.reenterPin.value.isEmpty()) {
                            isError.value = true
                            errStr.value = "FIELDS CANT BE EMPTY"
                        } else {
                            isError.value = false




                            onSubmit()
//                        manageViewModel.ResetPinToggleState.value=true

                        }

                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .width(156.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)


                ) {
                    Text(text = "Submit", fontSize = 14.sp, color = finocolor)
                }
                Button(
                    onClick = {
                        hotlist.value = !hotlist.value
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .width(156.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFA09D9D))
                ) {
                    Text("Cancel")
                }
            }


        }
    }

    @Composable
    fun EnterOTPPinSheet(state: MutableState<String>, oncancel: () -> Unit, onSubmit: () -> Unit) {
        val timer = remember {
            mutableStateOf(60)
        }
        val showTimer = remember {
            mutableStateOf(false)
        }
        Timer(timer, showTimer)
        showTimer.value = true

        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            else if(manageViewModel.isError.value){
//                CustomSheetAlertDialog(errMsg = manageViewModel.errorMessage.value) {
//                    manageViewModel.isError.value=false
//                  oncancel()
//                }
//            }
            if (manageViewModel.isLoading.value || cardDataViewModel.isLoading.value) {
                val rotate = remember {
                    mutableStateOf(false)
                }
                LaunchedEffect(key1 = true) {
                    rotate.value = true
                }
                val rotat = animateFloatAsState(
                    targetValue = if (rotate.value) 360f else 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            2000, 0, easing = EaseOutBack
                        ),
                    )
                )


                val animation by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(
                        R.raw.whitesq_loader
                    )
                )
                val progress by animateLottieCompositionAsState(
                    composition = animation, iterations = LottieConstants.IterateForever
                )

                LottieAnimation(
                    composition = animation,
                    progress = { progress },
                    modifier = Modifier
                        .size(90.dp)

                )


//
//                CustomSheetAlertDialog(errMsg = manageViewModel.errorMessage.value) {
//                    manageViewModel.isError.value=false
//                    oncancel()
//                }
            }

            if (manageViewModel.isError.value || cardDataViewModel.isError.value) {
                CustomSheetAlertDialog(errMsg = manageViewModel.errorMessage.value) {

                    oncancel()
                    manageViewModel.isError.value = false
                    cardDataViewModel.isError.value = false
                }
            } else {
                Column(
                    Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val comt = LocalContext.current
                    Text(
                        text = "Enter OTP",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color.White
                    )
                    BasicTextField(
                        value = state.value, onValueChange = {

                            if (it.length <= 4) {
                                state.value = it
                            }


                        }, keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done, keyboardType = KeyboardType.NumberPassword
                        )
                    )

                    {


                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            repeat(4) {

                                val char = when {
                                    it >= state.value.length -> ""
                                    else -> state.value[it]
                                }


                                Card(
                                    Modifier
                                        .padding(2.dp)
                                        .size(50.dp),
                                    colors = CardDefaults.cardColors(Color.White),
                                    shape = RoundedCornerShape(5.dp),

                                    elevation = CardDefaults.cardElevation(10.dp)
                                ) {

                                    Box(
                                        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                                    ) {

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
                    Text(
                        "Enter otp sent to your mobile",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    )

                }


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Button(
                        onClick = {
                            onSubmit()


                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .width(156.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)

                    ) {
                        Text(text = "Verify", fontSize = 14.sp, color = finocolor)
                    }
                    Button(
                        onClick = {
                            oncancel()
                            manageViewModel.Otp.value = ""
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .width(156.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFA09D9D))
                    ) {
                        Text("Cancel")
                    }

                }


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Resend Otp",
                        fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))),
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.clickable(enabled = timer.value == 0) {
                            timer.value = 60
                            verifyViewModel.sendOtp(num = SDK_CONSTANTS.mobileNumber,params = "CARD_OTP") {
                                if (it.status == "0") {
                                    showTimer.value = !showTimer.value
                                    Toast.makeText(
                                        context, "Otp sent Successfully ", Toast.LENGTH_SHORT
                                    ).show()
                                    verifyViewModel.verifyOtp.value = ""
                                }
                            }
                        })

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
        }
    }
    Box() {

        Scaffold(topBar = {
            CustomTopBar {
                rootNavController.popBackStack()
            }

        }, modifier = Modifier) {


//        if (manageViewModel.HotListToggleState.value) {
//            viewModel.isError.value=false
//            manageViewModel.isError.value=false
//            rootNavController.navigate(Destination.VIEW_CARDS_SCREEN)
//            manageViewModel.HotListToggleState.value=false
//        }
//        CustomScaffoldScreen(
//            sheet = ResetPinToggleState,
//            resetPinOtp = ResetPinOtpState,
//            blockCard = PauseCardToggleState,
//            blockCardOtp=PauseCardOtpToggleState,
//            hotlist = HotListToggleState,
//            hotlistCardOtp = manageViewModel.HotListOtpState,
//            cvv = CvvToggleState,
//            maskState = viewModel.mask,
//            details = DetailsState,
//            manageViewModel=manageViewModel
//        )
            var editTextValue by remember { mutableStateOf("") }
            var textFieldValue by remember { mutableStateOf("dbehera56@gmail.com") }

            Box(
                Modifier
                    .blur(
                        if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value || manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value || manageViewModel.cvvOtpSheetState.value || manageViewModel.viewBalanceOtpSheetState.value || manageViewModel.commingSoonSheet.value) {

                            10.dp
                        } else {

                            0.dp
                        }
                    )
                    .padding(it)
                    .fillMaxSize()

            ) {
                Column(

                    modifier = Modifier.fillMaxWidth().background(color = White).heightIn(300.dp)
                        .verticalScroll(enabled = true, state = ScrollState(0)),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Column(Modifier.fillMaxSize()) {
                        Box() {


                            FlipCard(
                                name = SDK_CONSTANTS.cardUser,
                                cardno = SDK_CONSTANTS.cardNumber,
                                exp = SDK_CONSTANTS.expiryDate,
                                avlbaln = SDK_CONSTANTS.availbalance,
                                cardfaceState = manageViewModel.cardFace,
                                manageViewModel.startanim,

                                viewBalance = {

                                    verifyViewModel.sendOtp(SDK_CONSTANTS.mobileNumber,params="View_Card_OTP"){
                                        if(it.status=="0"){
                                            manageViewModel.viewBalanceOtpSheetState.value =true

                                        }
                                        else{
                                            manageViewModel.isError.value=true
                                            manageViewModel.errorMessage.value=it.statusDesc
                                        }
                                    }



                                },


                                )


//
                        }






                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(
                                Modifier
                                    .height(50.dp)
                                    .background(
                                        light_finocolor, shape = RoundedCornerShape(
                                            topEnd = 50.dp, bottomEnd = 50.dp
                                        )
                                    ), verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Balance: ${if (manageViewModel.cardDataMask.value) "*****" else SDK_CONSTANTS.availbalance}",
                                    color = Color.White,
                                    modifier = Modifier.padding(5.dp)
                                )
                                Icon(painter = painterResource(id = R.drawable.baseline_currency_rupee_24),
                                    "",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .graphicsLayer {
                                            rotationX =
                                                if (!manageViewModel.cardDataMask.value) 360f else 0f
                                        })
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column() {
                                Text("CVV:", fontWeight = FontWeight(700), color = Color.Gray)
//                        Text(text=if(!CvvToggleState.value)"xxxx" else {
//                            if( manageViewModel.cvvValue.value .isEmpty()){
//                                "Fetching..."
//                            }
//                            else{
//                                manageViewModel.cvvValue.value
//                            }
//                        })


                                Switch(
                                    checked = manageViewModel.cvvUI.value,
                                    onCheckedChange = {

                                        if (manageViewModel.cvvValue.value.isEmpty()) {
                                            verifyViewModel.sendOtp(SDK_CONSTANTS.mobileNumber, params = "View_CVV_OTP"){
                                                if(it.status=="0"){
                                                    manageViewModel.cvvOtpSheetState.value = true
                                                }else{
                                                    manageViewModel.isError.value=true
                                                    manageViewModel.errorMessage.value=it.statusDesc
                                                }
                                            }

                                        } else {
                                            manageViewModel.cvvUI.value =
                                                !manageViewModel.cvvUI.value
                                            manageViewModel.cardFace.value =
                                                if (manageViewModel.cardFace.value == CardFace.Front) CardFace.Back else CardFace.Front

                                        }


//                                if(manageViewModel.cvvValue.value.isEmpty()) {
//                                    manageViewModel.CvvToggleState.value = it
//                                }


                                    },
                                    thumbContent = {
                                        Box(modifier = Modifier.size(50.dp)) {}
                                    },
                                    colors = SwitchDefaults.colors(
                                        checkedTrackColor = finocolor,
                                        uncheckedIconColor = Resetcolor,
                                        uncheckedThumbColor = finocolor,
                                        uncheckedBorderColor = Color.LightGray,
                                        checkedBorderColor = Color.Transparent,
                                        disabledCheckedBorderColor = Color.Transparent,
                                        disabledUncheckedBorderColor = Color.Transparent,
                                        disabledUncheckedIconColor = Resetcolor,
                                        uncheckedTrackColor = Color.White
                                    ),
                                )
                            }

                        }

                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxHeight(1f),
                        colors = CardDefaults.cardColors(
                            Color.White
                        ),
                        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                        elevation = CardDefaults.cardElevation(20.dp),
                    ) {

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            manageViewModel.list.forEachIndexed { index, s ->
                                Text(
                                    s,
                                    Modifier.clickable { manageViewModel.clickedState.value = s },
                                    color = if (s != manageViewModel.clickedState.value) Color.LightGray else {
                                        finocolor
                                    },
                                    fontWeight = FontWeight(
                                        if (s != manageViewModel.clickedState.value) 400 else {
                                            700
                                        }
                                    ),
                                    fontSize = if (s != manageViewModel.clickedState.value) 13.sp else {
                                        15.sp
                                    },
                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                )

                                if (index != manageViewModel.list.size - 1) {
                                    Spacer(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(1.dp)
                                            .background(color = Resetcolor)
                                    )
                                }
                            }
                        }



                        if (manageViewModel.clickedState.value == "Statement") {
                            Box(
                                Modifier
                                    .padding(vertical = 10.dp, horizontal = 10.dp)
                                    .fillMaxSize()
                                    .background(Transparent)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier.background(Transparent)
                                ) {
                                    Text(
                                        "Card Statement",
                                        fontWeight = FontWeight(600),
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(10.dp),
                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                                        color = finocolor
                                    )
                                    val checkList =
                                        listOf<String>("Last 10 Transaction", "Transaction History")
                                    checkList.forEach {
                                        CustomCheckBox(manageViewModel.checkBoxState, it)
                                    }
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(20.dp),
//                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                                ) {
//                                    CustomButton(
//                                        text = "SUBMIT", buttonColor = finocolor
//                                    ) {
//                                        if ( checkBoxState.value == "Last 10 Transaction") {
//                                            rootNavController.navigate(Destination.TRANSACTION_STATEMENTS_HISTORY + "/none")
//                                        } else {
//                                            rootNavController.navigate(Destination.TRANSACTION_STATEMENTS_HISTORY + "/${FilterOption.SelectDate}")
//                                        }
//                                    }
//                                    CustomButton(text = "CANCEL", buttonColor = cancelGray) {
//                                        rootNavController.popBackStack()
//                                    }
//
//                                }
                                }
                            }
                        } else if (manageViewModel.clickedState.value == "Managecard") {
                            Box(
                                Modifier
                                    .padding(vertical = 10.dp, horizontal = 10.dp)
                                    .fillMaxSize()
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        "Manage Card",
                                        fontWeight = FontWeight(600),
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(10.dp),
                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                                        color = finocolor
                                    )
                                    if (SDK_CONSTANTS.isVirtual == false || SDK_CONSTANTS.isVirtual == null) {
                                        CustomCheckField(
                                            state = manageViewModel.resetPinUI,
                                            text = if (SDK_CONSTANTS.isPinSet == true) "ResetPin" else "SetPin",
                                            res = R.drawable.group_one
                                        ) {
                                            verifyViewModel.sendOtp(num=SDK_CONSTANTS.mobileNumber, params = "RESET_OTP"){
                                                if(it.status=="0"){
                                                    manageViewModel.resetPinSheetState.value = true
                                                }
                                                else{
                                                    manageViewModel.isError.value=true
                                                    manageViewModel.errorMessage.value=it.statusDesc
                                                }
                                            }




                                        }
                                    }

                                    CustomCheckField(
                                        state = manageViewModel.blockCardUI,
                                        text = "Block",
                                        res = R.drawable.group_two
                                    ) {
                                        var params=if (SDK_CONSTANTS.isBlock == true) {

                                            PARAMS.unblock

                                        } else {

                                            PARAMS.block
                                        }
                                        verifyViewModel.sendOtp(num=SDK_CONSTANTS.mobileNumber,params=params){
                                            if(it.status=="0"){
                                                manageViewModel.blockCardSheetState.value = true
                                            }
                                            else{
                                                manageViewModel.isError.value=true
                                                manageViewModel.errorMessage.value=it.statusDesc
                                            }
                                        }

                                    }

//                                CustomCheckField(
//                                    state = manageViewModel.HotListToggleState,
//                                    text = "Hotlist card",
//                                    res = R.drawable.group_three
//                                ) {
//
//                                    HotListToggleState.value = !HotListToggleState.value
//
//                                    onClick(HotListToggleState.value)
//
//                                }

//                                CustomCheckField(
//                                    state = ReplaceToggleState,
//                                    text = "Replace card",
//                                    res = R.drawable.group_four
//                                ) {
//                                    ReplaceToggleState.value = !ReplaceToggleState.value
//                                    onClick(ReplaceToggleState.value)
//                                }

                                }
                            }
                        } else if (manageViewModel.clickedState.value == "Usage") {
//                        DetailsState.value
                            Box(
                                Modifier
                                    .padding(
                                        vertical = 10.dp, horizontal = 10.dp
                                    )
                                    .fillMaxSize()
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        "Usage",
                                        fontWeight = FontWeight(600),
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(10.dp),
                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                                        color = finocolor
                                    )
                                    ElevatedCard(
                                        modifier = Modifier.padding(
                                            horizontal = 20.dp, vertical = 10.dp
                                        ),
                                        colors = CardDefaults.elevatedCardColors(cdback),
                                        elevation = CardDefaults.elevatedCardElevation(2.dp)
                                    ) {
                                        Column(
                                            Modifier.padding(10.dp),
                                            verticalArrangement = Arrangement.spacedBy(15.dp)
                                        ) {
                                            Column(
                                                Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.spacedBy(0.dp),
//                                                        horizontalArrangement = Arrangement . SpaceBetween,
//                                                verticalAlignment = Alignment.CenterVertically
                                            ) {

                                                Text(
                                                    text = "ATM",
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight(600),
                                                    color = finocolor
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                val slidervalue = remember { mutableStateOf(0f) }
                                                Column {
                                                    Text((Math.round((slidervalue.value))).toString(),
                                                        Modifier
                                                            .graphicsLayer {
                                                                translationX =
                                                                    slidervalue.value / 15
                                                                translationY = 20f
                                                            }
                                                            .background(
                                                                finocolor,
                                                                shape = RoundedCornerShape(15.dp)
                                                            )
                                                            .padding(horizontal = 15.dp),
                                                        color = White,
                                                        fontSize = 15.sp,
                                                        fontWeight = FontWeight(600))



                                                    Slider(state = SliderState(
                                                        initialValue = slidervalue.value,
                                                        steps = 10,
                                                        valueRange = 0f..10000f,
                                                        initialOnValueChange = {

                                                            slidervalue.value = it

                                                        },
                                                    ),
                                                        thumb = {
                                                            Column() {

                                                                Box(
                                                                    Modifier
                                                                        .size(30.dp)
                                                                        .background(
                                                                            finocolor,
                                                                            RoundedCornerShape(50.dp)
                                                                        ),
                                                                    contentAlignment = Alignment.Center
                                                                ) {

                                                                    Box(
                                                                        Modifier
                                                                            .size(20.dp)
                                                                            .background(
                                                                                Color.White,
                                                                                RoundedCornerShape(
                                                                                    50.dp
                                                                                )
                                                                            )
                                                                    )

                                                                }
                                                            }
                                                        },
                                                        track = {

                                                            Row(
                                                                Modifier
                                                                    .height(10.dp)
                                                                    .fillMaxWidth()
                                                                    .padding(horizontal = 2.dp)
                                                                    .background(
                                                                        finocolor,
                                                                        RoundedCornerShape(10.dp)
                                                                    ),
                                                                verticalAlignment = Alignment.CenterVertically
                                                            ) {
                                                                Box(
                                                                    Modifier
                                                                        .height(7.dp)
                                                                        .padding(horizontal = 2.dp)
                                                                        .fillMaxWidth(it.value / 10000)
                                                                        .background(
                                                                            Color.White,
                                                                            RoundedCornerShape(10.dp)
                                                                        )
                                                                )
                                                            }
                                                        },
                                                        colors = androidx.compose.material3.SliderDefaults.colors(
                                                            thumbColor = finocolor,
                                                            activeTrackColor = finocolor.copy(0.5f),
                                                            disabledActiveTrackColor = Color.Gray
                                                        )
                                                    )
                                                }


                                            }
//                                            Row(
//                                                Modifier.fillMaxWidth(),
//                                                horizontalArrangement = Arrangement.SpaceBetween,
//                                                verticalAlignment = Alignment.CenterVertically
//                                            ) {
//
//                                                Text(
//                                                    text = "POS",
//                                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                                    fontSize = 18.sp,
//                                                    fontWeight = FontWeight(700),
//                                                )
//                                                Spacer(modifier = Modifier.width(20.dp))
//                                                Column {
//                                                    Row(
//                                                        modifier = Modifier
//                                                            .fillMaxWidth()
//                                                            .padding(horizontal = 10.dp)
//                                                            .graphicsLayer {
//                                                                translationY = -30f
//                                                            },
//                                                        horizontalArrangement = Arrangement.SpaceBetween
//                                                    ) {
//
//                                                        Text(
//                                                            "0",
//                                                            fontWeight = FontWeight(900),
//                                                            fontSize = 10.sp,
//                                                            color = finocolor
//                                                        )
//
//
//                                                        Text(
//                                                            "10000",
//                                                            fontWeight = FontWeight(900),
//                                                            fontSize = 10.sp,
//                                                            color = finocolor
//                                                        )
//                                                    }
//                                                }
//
//
//                                            }
                                            Column(
                                                Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.spacedBy(0.dp)
                                            ) {

                                                Text(
                                                    text = "POS",
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight(600),
                                                    color = finocolor
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                val slidervalue = remember { mutableStateOf(0f) }
                                                Column {
                                                    Text((Math.round((slidervalue.value))).toString(),
                                                        Modifier
                                                            .graphicsLayer {
                                                                translationX =
                                                                    slidervalue.value / 15
                                                                translationY = 20f
                                                            }
                                                            .background(
                                                                finocolor,
                                                                shape = RoundedCornerShape(15.dp)
                                                            )
                                                            .padding(horizontal = 15.dp),
                                                        color = White,
                                                        fontSize = 15.sp,
                                                        fontWeight = FontWeight(600))



                                                    Slider(state = SliderState(
                                                        initialValue = slidervalue.value,
                                                        steps = 10,
                                                        valueRange = 0f..10000f,
                                                        initialOnValueChange = {

                                                            slidervalue.value = it

                                                        },
                                                    ),
                                                        thumb = {
                                                            Column() {

                                                                Box(
                                                                    Modifier
                                                                        .size(30.dp)
                                                                        .background(
                                                                            finocolor,
                                                                            RoundedCornerShape(50.dp)
                                                                        ),
                                                                    contentAlignment = Alignment.Center
                                                                ) {

                                                                    Box(
                                                                        Modifier
                                                                            .size(20.dp)
                                                                            .background(
                                                                                Color.White,
                                                                                RoundedCornerShape(
                                                                                    50.dp
                                                                                )
                                                                            )
                                                                    )

                                                                }
                                                            }
                                                        },
                                                        track = {

                                                            Row(
                                                                Modifier
                                                                    .height(10.dp)
                                                                    .fillMaxWidth()
                                                                    .padding(horizontal = 2.dp)
                                                                    .background(
                                                                        finocolor,
                                                                        RoundedCornerShape(10.dp)
                                                                    ),
                                                                verticalAlignment = Alignment.CenterVertically
                                                            ) {
                                                                Box(
                                                                    Modifier
                                                                        .height(7.dp)
                                                                        .padding(horizontal = 2.dp)
                                                                        .fillMaxWidth(it.value / 10000)
                                                                        .background(
                                                                            Color.White,
                                                                            RoundedCornerShape(10.dp)
                                                                        )
                                                                )
                                                            }
                                                        },
                                                        colors = androidx.compose.material3.SliderDefaults.colors(
                                                            thumbColor = finocolor,
                                                            activeTrackColor = finocolor.copy(0.5f),
                                                            disabledActiveTrackColor = Color.Gray
                                                        )
                                                    )
                                                }


                                            }
//                                            Row(
//                                                Modifier.fillMaxWidth(),
//                                                horizontalArrangement = Arrangement.SpaceBetween,
//                                                verticalAlignment = Alignment.CenterVertically
//                                            ) {
//
//                                                Text(
//                                                    text = "ECOM",
//                                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                                    fontSize = 18.sp,
//                                                    fontWeight = FontWeight(700),
//                                                )
//                                                Spacer(modifier = Modifier.width(10.dp))
//
//                                                Column {
//                                                    Row(
//                                                        modifier = Modifier
//                                                            .fillMaxWidth()
//                                                            .padding(horizontal = 10.dp)
//                                                            .graphicsLayer {
//                                                                translationY = -30f
//                                                            },
//                                                        horizontalArrangement = Arrangement.SpaceBetween
//                                                    ) {
//
//                                                        Text(
//                                                            "0",
//                                                            fontWeight = FontWeight(900),
//                                                            fontSize = 10.sp,
//                                                            color = finocolor
//                                                        )
//
//
//                                                        Text(
//                                                            "10000",
//                                                            fontWeight = FontWeight(900),
//                                                            fontSize = 10.sp,
//                                                            color = finocolor
//                                                        )
//                                                    }
//                                                }
//
//
//                                            }
                                            Column(
                                                Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.spacedBy(0.dp)
                                            ) {

                                                Text(
                                                    "ECOM",
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight(600),
                                                    color = finocolor
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                val slidervalue = remember { mutableStateOf(0f) }
                                                Column {
                                                    Text((Math.round((slidervalue.value))).toString(),
                                                        Modifier
                                                            .graphicsLayer {
                                                                translationX =
                                                                    slidervalue.value / 15
                                                                translationY = 20f
                                                            }
                                                            .background(
                                                                finocolor,
                                                                shape = RoundedCornerShape(15.dp)
                                                            )
                                                            .padding(horizontal = 15.dp),
                                                        color = White,
                                                        fontSize = 15.sp,
                                                        fontWeight = FontWeight(600))



                                                    Slider(state = SliderState(
                                                        initialValue = slidervalue.value,
                                                        steps = 10,
                                                        valueRange = 0f..10000f,
                                                        initialOnValueChange = {

                                                            slidervalue.value = it

                                                        },
                                                    ),
                                                        thumb = {
                                                            Column() {

                                                                Box(
                                                                    Modifier
                                                                        .size(30.dp)
                                                                        .background(
                                                                            finocolor,
                                                                            RoundedCornerShape(50.dp)
                                                                        ),
                                                                    contentAlignment = Alignment.Center
                                                                ) {

                                                                    Box(
                                                                        Modifier
                                                                            .size(20.dp)
                                                                            .background(
                                                                                Color.White,
                                                                                RoundedCornerShape(
                                                                                    50.dp
                                                                                )
                                                                            )
                                                                    )

                                                                }
                                                            }
                                                        },
                                                        track = {

                                                            Row(
                                                                Modifier
                                                                    .height(10.dp)
                                                                    .fillMaxWidth()
                                                                    .padding(horizontal = 2.dp)
                                                                    .background(
                                                                        finocolor,
                                                                        RoundedCornerShape(10.dp)
                                                                    ),
                                                                verticalAlignment = Alignment.CenterVertically
                                                            ) {
                                                                Box(
                                                                    Modifier
                                                                        .height(7.dp)
                                                                        .padding(horizontal = 2.dp)
                                                                        .fillMaxWidth(it.value)
                                                                        .background(
                                                                            Color.White,
                                                                            RoundedCornerShape(10.dp)
                                                                        )
                                                                )
                                                            }
                                                        },
                                                        colors = androidx.compose.material3.SliderDefaults.colors(
                                                            thumbColor = finocolor,
                                                            activeTrackColor = finocolor.copy(0.5f),
                                                            disabledActiveTrackColor = Color.Gray
                                                        )
                                                    )
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(horizontal = 10.dp)
                                                            .graphicsLayer {
                                                                translationY = -30f
                                                            },
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {

                                                        Text(
                                                            "0",
                                                            fontWeight = FontWeight(900),
                                                            fontSize = 10.sp,
                                                            color = finocolor
                                                        )


                                                        Text(
                                                            "10000",
                                                            fontWeight = FontWeight(900),
                                                            fontSize = 10.sp,
                                                            color = finocolor
                                                        )
                                                    }
                                                }


                                            }


                                        }

                                    }
                                }
                            }
                        } else {
                            Box() {
                                Column(
                                    modifier = Modifier
                                        .height(400.dp)
                                        .verticalScroll(ScrollState(0), enabled = true)
                                ) {
                                    Box() {
                                        Column(
                                            modifier = Modifier
                                                .height(400.dp)
                                                .verticalScroll(ScrollState(0), enabled = true)
                                        ) {


                                            Box(
                                                Modifier
                                                    .padding(
                                                        vertical = 10.dp, horizontal = 10.dp
                                                    )
                                                    .fillMaxHeight(0.4f)
                                                    .fillMaxWidth()
                                            ) {
                                                Column(horizontalAlignment = Alignment.Start) {

                                                    Text(
                                                        "Services",
                                                        fontWeight = FontWeight(600),
                                                        fontSize = 20.sp,
                                                        modifier = Modifier.padding(10.dp),
                                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                                                        color = finocolor
                                                    )
                                                    if (!SDK_CONSTANTS.cardType.equals(
                                                            "GIFT", true
                                                        )
                                                    ) {

                                                        if (!SDK_CONSTANTS.cardType.equals(
                                                                "GIFT", true
                                                            )
                                                        ) {

                                                            CustomCheckBox(
                                                                manageViewModel.serviceRadioState,
                                                                "Add Money"
                                                            )
                                                            if (!SDK_CONSTANTS.kycType.equals("min_kyc")) {
                                                                CustomCheckBox(
                                                                    manageViewModel.serviceRadioState,
                                                                    "Send Money"
                                                                )
                                                            }
                                                        }
                                                        if (SDK_CONSTANTS.isVirtual == true) {
                                                            CustomCheckBox(
                                                                manageViewModel.serviceRadioState,
                                                                "Order PhysicalCatd"
                                                            )
                                                        }
                                                    }

                                                }
                                                if (SDK_CONSTANTS.isVirtual == true) {
                                                    CustomCheckBox(
                                                        manageViewModel.serviceRadioState,
                                                        "Order PhysicalCatd"
                                                    )
                                                }
                                            }

                                        }
//                            if(SDK_CONSTANTS.cardType.equals("GIFT",true) && SDK_CONSTANTS.isVirtual==false){
//
//                                Text("NO services Availabel for this card",color=Color.LightGray, modifier = Modifier.padding(start=10.dp))
//                            }else
//                            {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(horizontal = 20.dp),
//                                horizontalArrangement = Arrangement.SpaceEvenly
//                            ) {
//                                CustomButton(
//                                    text = "SUBMIT", buttonColor = finocolor
//                                ) {
//
//                                }
//                                CustomButton(text = "CANCEL", buttonColor = cancelGray) {
//                                    rootNavController.popBackStack()
//                                }
//
//                            }}
                                    }
                                }
                            }
                        }
                    }
                }
            }
//                   if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value || manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value || manageViewModel.cvvOtpSheetState.value || manageViewModel.viewBalanceOtpSheetState.value||manageViewModel.commingSoonSheet.value) {
//                       Box(
//                           Modifier
//                               .padding(it)
//                               .fillMaxSize()
//                               .background(
//                                   color = if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value || manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value || manageViewModel.cvvOtpSheetState.value || manageViewModel.viewBalanceOtpSheetState.value || manageViewModel.commingSoonSheet.value) {
//                                       Color.LightGray.copy(0.5f)
//                                   } else {
//                                       Color.White
//                                   }
//                               )
//                       )
//                   }

        }
        CustomSheetWrap(state = mutableStateOf(manageViewModel.clickedState.value.equals("Statement"))) {

            if (SDK_CONSTANTS.cardType.equals("GIFT", true) && SDK_CONSTANTS.isVirtual == false) {

                Text(
                    "NO services Availabel for this card",
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 10.dp)
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedButton(
                        onClick = {

                            if (manageViewModel.checkBoxState.value == "Last 10 Transaction") {
                                rootNavController.navigate(Destination.TRANSACTION_STATEMENTS_HISTORY + "/none")
                            } else {
                                rootNavController.navigate(Destination.TRANSACTION_STATEMENTS_HISTORY + "/${FilterOption.SelectDate}")
                            }

                        }, colors = ButtonDefaults.elevatedButtonColors(
                            finocolor
                        ), shape = RoundedCornerShape(5.dp), modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "PROCEED",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(
                                Font(R.font.poppins_regular)
                            )
                        )
                    }
                }
            }


        }


        CustomSheetWrap(state = mutableStateOf(manageViewModel.clickedState.value.equals("Services"))) {
            if (SDK_CONSTANTS.cardType.equals("GIFT", true) && SDK_CONSTANTS.isVirtual == false) {

                Text(
                    "NO services Availabel for this card",
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 10.dp)
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedButton(
                        onClick = {
//                                             manageViewModel.commingSoonSheet.value=true
//                        scope.launch {
//                            delay(2000)
//                            manageViewModel.commingSoonSheet.value=false
                            if (manageViewModel.serviceRadioState.value.equals("Send Money")) {
                                rootNavController.navigate(Destination.SELECT_BENE)
                            }
//                        }


                        }, colors = ButtonDefaults.elevatedButtonColors(
                            finocolor
                        ), shape = RoundedCornerShape(5.dp), modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "PROCEED",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontFamily = FontFamily(
                                Font(R.font.poppins_regular)
                            )
                        )
                    }

//    if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value|| manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value||manageViewModel.cvvOtpSheetState.value )  {
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(
//                    Color.LightGray.copy(0.2f)
//
//                )
//                .pointerInput(null, null, {})
//        ) {
//
//        }
//    }


                }
            }
        }
        CustomSheetWrap(state = manageViewModel.commingSoonSheet) {
            val lottie by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.comminsoon)

            )
            val startAnim = remember {
                mutableStateOf(false)
            }

            val progress by animateLottieCompositionAsState(composition = lottie)
            LottieAnimation(composition = lottie, progress = { progress }, Modifier.size(200.dp))


        }
        if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value || manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value || manageViewModel.cvvOtpSheetState.value || manageViewModel.viewBalanceOtpSheetState.value || manageViewModel.commingSoonSheet.value) {

            Box(
                Modifier
                    .fillMaxSize()
                    .background(Gray.copy(0.5f))
                    .pointerInput(null, null, {})
            ) {

            }
        }
        CustomSheetWrap(
            state = manageViewModel.resetPinSheetState, initOffset = 2000, color = light_finocolor
        ) {
//            Sheet("", manageViewModel.cvvUI, {}) {}
            ResetPinSheet(hotlist = manageViewModel.resetPinSheetState, color = light_finocolor) {
                manageViewModel.resetPinSheetState.value = false
                manageViewModel.enterPin.value = ""
                manageViewModel.reenterPin.value = ""
                manageViewModel.resetPinOtpSheetState.value = true
            }
        }
        CustomSheetWrap(
            state = manageViewModel.resetPinOtpSheetState,
            delay = manageViewModel.delay.value,
            initOffset = 1000,
            color = if (manageViewModel.isError.value) Red.copy(0.6f) else light_finocolor
        ) {
            EnterOTPPinSheet(state = manageViewModel.Otp,
                oncancel = { manageViewModel.resetPinOtpSheetState.value = false }) {

                manageViewModel.resetPin {
                    if (it != null) {
                        if (it.status == "0") {
                            sucess.value = true
                            sucessMsg.value = "You have Sucessfully completed pin reset"
                            SDK_CONSTANTS.isPinSet = true
                            manageViewModel.resetPinOtpSheetState.value = false
                            manageViewModel.Otp.value = ""
                        } else {
                            manageViewModel.isError.value = true
                            manageViewModel.errorMessage.value = it.statusDesc
                            SDK_CONSTANTS.isPinSet = false

                            manageViewModel.Otp.value = ""
                        }
                    } else {
                        manageViewModel.isError.value = true
                        manageViewModel.errorMessage.value = it.statusDesc
                        manageViewModel.Otp.value = ""
                    }
                    Toast.makeText(context, it.statusDesc, Toast.LENGTH_LONG).show()
                }


            }
        }
        CustomSheetWrap(
            state = manageViewModel.blockOtpSheetState,
            delay = manageViewModel.delay.value,
            initOffset = 1000,
            color = if (manageViewModel.isError.value) Red.copy(0.6f) else light_finocolor
        ) {
            EnterOTPPinSheet(state = manageViewModel.Otp,
                oncancel = { manageViewModel.blockOtpSheetState.value = false }) {

                val stat = if (SDK_CONSTANTS.isBlock == true) {

                     "unblock"

                } else {
                    "block"
                }
                manageViewModel.changeCardStatus(status = stat , onSucesss = {
                    if (it != null) {
                        if (it.status == "0") {
                            sucess.value = true
                            sucessMsg.value = "SucessFully ${stat}ed"
                            manageViewModel.blockOtpSheetState.value = false
                            manageViewModel.Otp.value = ""

                        }
                    }
                    else{
                        manageViewModel.Otp.value = ""
                    }
                    Toast.makeText(context, it.statusDesc, Toast.LENGTH_LONG).show()

                    if (it.status.equals("0")) {
                        manageViewModel.blockCardUI.value = true
                    } else {
                        manageViewModel.isError.value = true
                        manageViewModel.errorMessage.value = it.statusDesc

                    }

                })



            }
        }

        CustomSheetWrap(
            state = manageViewModel.cvvOtpSheetState,
            delay = manageViewModel.delay.value,
            initOffset = 1000,
            color = if (manageViewModel.isError.value) Red.copy(0.6f) else light_finocolor
        ) {

            EnterOTPPinSheet(state = manageViewModel.Otp, oncancel = {
                manageViewModel.cvvOtpSheetState.value = false

            }) {


                if (manageViewModel.cvvValue.value.isEmpty()) {
                    manageViewModel.viewCvv() {
                        if (it != null) {
                            if (it.status == "0") {
                                sucess.value = true
                                sucessMsg.value = "Cvv sucessfully fetched"
                                manageViewModel.Otp.value = ""
                                manageViewModel.cvvOtpSheetState.value = false
                                manageViewModel.cvvUI.value = !manageViewModel.cvvUI.value
                                manageViewModel.cardFace.value =
                                    if (manageViewModel.cardFace.value == CardFace.Front) CardFace.Back else CardFace.Front

                            } else {
                                manageViewModel.isError.value = true
                                manageViewModel.errorMessage.value = it.statusDesc
                                manageViewModel.Otp.value = ""
                            }
                        }

                        Toast.makeText(context, it.statusDesc, Toast.LENGTH_LONG).show()
                        if (it.status.equals("0")) {


                        }
                    }
                }


            }
        }
        CustomSheetWrap(state = manageViewModel.blockCardSheetState) {
            val msg = if (SDK_CONSTANTS.isBlock == true) {
                STRING.UNBLOCK_CARD
            } else {
                STRING.BLOCK_CARD
            }
            Sheet(str = msg, hotlist = manageViewModel.blockCardSheetState, onCancel = {
                manageViewModel.blockCardSheetState.value =
                    !manageViewModel.blockCardSheetState.value
            }) {
                manageViewModel.blockCardSheetState.value =
                    !manageViewModel.blockCardSheetState.value
                manageViewModel.blockOtpSheetState.value = true
                manageViewModel.delay.value = 1000

            }
            manageViewModel.Otp.value = ""
        }
        CustomSheetWrap(
            state = manageViewModel.viewBalanceOtpSheetState,
            delay = manageViewModel.delay.value,
            initOffset = 1000,
            color = light_finocolor
        ) {

            EnterOTPPinSheet(state = cardDataViewModel.otp, oncancel = {
                manageViewModel.viewBalanceOtpSheetState.value = false


            }) {
                cardDataViewModel.viewCardData(SDK_CONSTANTS.customerId, SDK_CONSTANTS.cardNumber) {
                    if (it != null) {
                        if (it.status == "0") {
                            sucess.value = true
                            sucessMsg.value = "Data sucessfully fetched"
                            cardDataViewModel.otp.value=""
                            manageViewModel.cardDataMask.value = false
                            manageViewModel.viewBalanceOtpSheetState.value = false
                            cardDataViewModel.otp.value = ""
                        } else {
                            manageViewModel.isError.value = true
                            manageViewModel.errorMessage.value = it.statusDesc
                            cardDataViewModel.otp.value=""

                        }
                    } else {
                        manageViewModel.isError.value = true
                        manageViewModel.errorMessage.value = "Something Went Wrong"
                        cardDataViewModel.otp.value=""
                    }

                }



            }

        }

        CustomSheetWrap(state = sucess) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val lottie by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(R.raw.sucess)

                )
                val startAnim = remember {
                    mutableStateOf(false)
                }

                val progress by animateLottieCompositionAsState(composition = lottie)

                Text(sucessMsg.value)

                LottieAnimation(
                    composition = lottie,
                    progress = { progress },
                    modifier = Modifier
                        .height(150.dp)
                        .padding(0.dp)
                )

            }


        }
    }




//    if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value|| manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value||manageViewModel.cvvOtpSheetState.value )  {
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(
//                    Color.LightGray.copy(0.2f)
//
//                )
//                .pointerInput(null, null, {})
//        ) {
//
//        }
//    }


}



