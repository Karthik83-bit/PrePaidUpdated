package com.example.prepaidcard.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.RepeatMode
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
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

import com.example.prepaidcard.components.CustomButton
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
import com.example.prepaidcardsdk.presentation.viewmodels.CardDataViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.GeneratePinViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.ui.theme.Resetcolor
import com.example.prepaidcardsdk.ui.theme.cancelGray
import com.example.prepaidcardsdk.ui.theme.cdback
import com.example.prepaidcardsdk.ui.theme.lighttealGreen
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardManagementScreen(
    rootNavController: NavHostController,
    viewModel: GeneratePinViewModel,
    cardDataViewModel:CardDataViewModel,
    onClick: (state: Boolean) -> Unit = {},
    manageViewModel: ManageCardViewModel,
) {
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

    @Composable
    fun ResetPinSheet(hotlist: MutableState<Boolean>,onSubmit:()->Unit) {
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
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Enter New PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
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
                placeholder = { Text("Enetr PIN") },
                onValueChange = { manageViewModel.enterPin.value = it },
                shape = RoundedCornerShape(2.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.textFieldColors(

                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
                )
                Text(
                    text = "Re-nter New PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
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
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.textFieldColors(

                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)


                ) {
                    Text(text = "Submit", fontSize = 14.sp)
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
            if (manageViewModel.isLoading.value||cardDataViewModel.isLoading.value)
            {
                val rotate = remember {
                    mutableStateOf(false)
                }
                LaunchedEffect(key1 = true,) {
                    rotate.value = true
                }
                val rotat =
                    animateFloatAsState(
                        targetValue = if (rotate.value) 360f else 0f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                2000,
                                0,
                                easing = EaseOutBack
                            ),
                        )
                    )


                val animation by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(
                        R.raw.cardscan
                    )
                )
                val progress by animateLottieCompositionAsState(
                    composition = animation,
                    iterations = LottieConstants.IterateForever
                )

                LottieAnimation(
                    composition = animation,
                    progress = { progress },
                    modifier = Modifier
                        .size(90.dp)
                        .rotate(rotat.value)
                )


//
//                CustomSheetAlertDialog(errMsg = manageViewModel.errorMessage.value) {
//                    manageViewModel.isError.value=false
//                    oncancel()
//                }
            }

            if (manageViewModel.isError.value||cardDataViewModel.isError.value){
                CustomSheetAlertDialog(errMsg = manageViewModel.errorMessage.value) {
                    oncancel()
                }
            }else{
                Column(
                    Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val comt = LocalContext.current
                    Text(
                        text = "Enter OTP",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    )
                    BasicTextField(
                        value = state.value,
                        onValueChange = {

                            if (it.length <= 4) {
                                state.value = it
                            }


                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.NumberPassword
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
                                    colors = CardDefaults.cardColors(Color.LightGray),
                                    shape = RoundedCornerShape(5.dp),

                                    elevation = CardDefaults.cardElevation(10.dp)
                                ) {

                                    Box(
                                        Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
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
                            onClick = { onSubmit()

                                      manageViewModel.Otp.value=""},
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp)
                                .width(156.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)

                        ) {
                            Text(text = "Verify", fontSize = 14.sp)
                        }
                        Button(
                            onClick = {
                                oncancel()
                                manageViewModel.Otp.value=""
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



                Text("Remaining Time: ${"sec"}")}




        }
    }

    Scaffold(topBar = {
        CustomTopBar {
            rootNavController.popBackStack()
        }

    },   modifier=Modifier.blur(
        if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value|| manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value||manageViewModel.cvvOtpSheetState.value ) {
            10.dp
        } else {
            0.dp
        }
    ))
    {





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
                .padding(it)
                .fillMaxSize()
                .background(
                    color = if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value || manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value || manageViewModel.cvvOtpSheetState.value) {
                        Color.Black.copy(0.5f)
                    } else {
                        Color.Transparent
                    }
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .verticalScroll(enabled = true, state = ScrollState(0))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                FlipCard(
                    name = SDK_CONSTANTS.cardUser,
                    cardno = SDK_CONSTANTS.cardNumber,
                    exp = SDK_CONSTANTS.expiryDate,
                    avlbaln = SDK_CONSTANTS.availbalance,
                    cardfaceState = manageViewModel.cardFace
                ) {
                    manageViewModel.viewBalanceOtp.value =
                        !manageViewModel.viewBalanceOtp.value
                }
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text("CVV:", fontWeight = FontWeight(700))
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
                            if(manageViewModel.cvvValue.value.isEmpty()){
                                manageViewModel.cvvOtpSheetState.value = true
                            }else{
                                manageViewModel.cvvUI.value=!manageViewModel.cvvUI.value
                                manageViewModel.cardFace.value=if(manageViewModel.cardFace.value==CardFace.Front) CardFace.Back else CardFace.Front

                            }


//                                if(manageViewModel.cvvValue.value.isEmpty()) {
//                                    manageViewModel.CvvToggleState.value = it
//                                }


                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Resetcolor,
                            uncheckedIconColor = Resetcolor,
                            uncheckedBorderColor = Resetcolor,
                            disabledUncheckedIconColor = Resetcolor
                        ),
                    )

                }

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
                            color = if (s != manageViewModel.clickedState.value) Resetcolor else {
                                Color(0xFFDB8726)
                            },
                            fontWeight = FontWeight(600)
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
                val checkBoxState = remember {
                    mutableStateOf("")
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(Color.LightGray)
                )

                if (manageViewModel.clickedState.value == "Statement") {
                    Box(
                        Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .fillMaxSize()
                    ) {
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                "Card Statement",
                                fontWeight = FontWeight(600),
                                fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp),
                                fontFamily = FontFamily(Font(R.font.lato_bold))
                            )
                            val checkList =
                                listOf<String>("Last 10 Transaction", "Transaction History")
                            checkList.forEach {
                                CustomCheckBox(checkBoxState, it)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                CustomButton(
                                    text = "SUBMIT", buttonColor = lighttealGreen
                                ) {
                                    if (checkBoxState.value == "Last 10 Transaction") {
                                        rootNavController.navigate(Destination.TRANSACTION_STATEMENTS_HISTORY + "/none")
                                    } else {
                                        rootNavController.navigate(Destination.TRANSACTION_STATEMENTS_HISTORY + "/${FilterOption.SelectDate}")
                                    }
                                }
                                CustomButton(text = "CANCEL", buttonColor = cancelGray) {
                                    rootNavController.popBackStack()
                                }

                            }
                        }
                    }
                } else if (manageViewModel.clickedState.value == "Managecard")
                {
                    Box(
                        Modifier
                            .padding(vertical = 10.dp)
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
                                fontFamily = FontFamily(Font(R.font.lato_bold))
                            )
                            if(SDK_CONSTANTS.cardType.equals("GIFT")){
                                CustomCheckField(
                                    state = manageViewModel.resetPinUI,
                                    text =  "Reset Pin",
                                    res = R.drawable.group_one
                                ) {

                                        manageViewModel.resetPinSheetState.value=true



                                }}

                                CustomCheckField(
                                    state = manageViewModel.blockCardUI,
                                    text = "Block",
                                    res = R.drawable.group_two
                                ) {
                                    manageViewModel.blockCardSheetState.value=true
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
                } else if (manageViewModel.clickedState.value == "Details")
                {
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
                                "Card Details",
                                fontWeight = FontWeight(600),
                                fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp),
                                fontFamily = FontFamily(Font(R.font.lato_bold))
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
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Text(
                                            text = "Email:",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 12.sp
                                        )
                                        BasicTextField(
                                            value = viewModel.username.value,
                                            enabled = viewModel.mask.value != 10.dp,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Email,
                                                imeAction = ImeAction.Done
                                            ),
                                            textStyle = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                                fontSize = 14.sp
                                            ),
                                            onValueChange = {
                                                viewModel.username.value = it
                                            },
                                            modifier = Modifier.blur(viewModel.mask.value)
                                        )
                                    }
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Mobile:",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = "7978730692",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 14.sp,
                                            modifier = Modifier.blur(viewModel.mask.value)
                                        )
                                    }
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Monthly Limit:",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 12.sp
                                        )

                                        BasicTextField(
                                            value = viewModel.monthlyLimit.value,
                                            enabled = viewModel.mask.value != 10.dp,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Done
                                            ),
                                            textStyle = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                                fontSize = 14.sp
                                            ),
                                            onValueChange = {
                                                viewModel.monthlyLimit.value = it
                                            },
                                            modifier = Modifier.blur(viewModel.mask.value)
                                        )
                                    }
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Card Limit:",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 12.sp
                                        )

                                        BasicTextField(
                                            value = viewModel.cardLimit.value,
                                            enabled = viewModel.mask.value != 10.dp,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Done
                                            ),
                                            textStyle = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                                fontSize = 14.sp
                                            ),
                                            onValueChange = {
                                                viewModel.cardLimit.value = it
                                            },
                                            modifier = Modifier.blur(viewModel.mask.value)
                                        )
                                    }
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "KYC Status:",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = "Completed",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 14.sp,
                                            modifier = Modifier.blur(viewModel.mask.value)
                                        )
                                    }
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Card Status:",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = "Active",
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            fontSize = 14.sp,
                                            modifier = Modifier.blur(viewModel.mask.value)
                                        )
                                    }
                                }

                            }
                        }
                    }
                } else {
                    Column(){


                        Box(
                            Modifier
                                .padding(vertical = 10.dp, horizontal = 10.dp)
                                .fillMaxHeight(0.4f).fillMaxWidth()
                        ) {
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(
                                    "Services",
                                    fontWeight = FontWeight(600),
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp),
                                    fontFamily = FontFamily(Font(R.font.lato_bold))
                                )

                                CustomCheckBox(manageViewModel.serviceRadioState, "Add Money")
                                CustomCheckBox(manageViewModel.serviceRadioState, "Send Money")
                                CustomCheckBox(
                                    manageViewModel.serviceRadioState, "Order PhysicalCatd")
                            }

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CustomButton(
                                text = "SUBMIT", buttonColor = lighttealGreen
                            ) {

                            }
                            CustomButton(text = "CANCEL", buttonColor = cancelGray) {
                                rootNavController.popBackStack()
                            }

                        }}
                    }
                }
            }

        }

    if (manageViewModel.blockCardSheetState.value || manageViewModel.resetPinSheetState.value|| manageViewModel.blockOtpSheetState.value || manageViewModel.resetPinOtpSheetState.value||manageViewModel.cvvOtpSheetState.value )  {
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(0.5f)

                )
                .pointerInput(null, null, {})
        ) {

        }
    }

    CustomSheetWrap(state =manageViewModel.resetPinSheetState, initOffset = 2000 ) {
//            Sheet("", manageViewModel.cvvUI, {}) {}
        ResetPinSheet(hotlist = manageViewModel.resetPinSheetState){
            manageViewModel.resetPinSheetState.value=false
            manageViewModel.enterPin.value=""
            manageViewModel.reenterPin.value=""
            manageViewModel.resetPinOtpSheetState.value=true
        }
        manageViewModel.enterPin.value=""
        manageViewModel.reenterPin.value=""
    }
    CustomSheetWrap(state = manageViewModel.resetPinOtpSheetState, delay = manageViewModel.delay.value, initOffset = 1000) {
        EnterOTPPinSheet(state = manageViewModel.Otp, oncancel = { manageViewModel.resetPinOtpSheetState.value=false })
        {
            manageViewModel.resetPin {

            }

            manageViewModel.resetPinOtpSheetState.value=false
            manageViewModel.Otp.value=""
        }
    }
    CustomSheetWrap(state = manageViewModel.blockOtpSheetState, delay = manageViewModel.delay.value,initOffset = 1000) {
        EnterOTPPinSheet(state = manageViewModel.Otp, oncancel = { manageViewModel.blockOtpSheetState.value=false })
        {
            manageViewModel.changeCardStatus(status = "block"){

                if(it.status.equals("0")){
                    manageViewModel.blockCardUI.value=true
                }
                else{
                    manageViewModel.blockCardUI.value=false
                }

            }
            manageViewModel.Otp.value=""



        }
    }

    CustomSheetWrap(state = manageViewModel.cvvOtpSheetState, delay = manageViewModel.delay.value,initOffset = 1000) {

        EnterOTPPinSheet(state = manageViewModel.Otp, oncancel = { manageViewModel.cvvOtpSheetState.value=false

        })
        {

            if(manageViewModel.cvvValue.value.isEmpty()) {
                manageViewModel.viewCvv() {
                        if(it.status.equals("0")){



                        }
                }
            }
            manageViewModel.cvvUI.value=!manageViewModel.cvvUI.value
            manageViewModel.cardFace.value=if(manageViewModel.cardFace.value==CardFace.Front) CardFace.Back else CardFace.Front

            manageViewModel.cvvOtpSheetState.value=false
            manageViewModel.Otp.value=""
        }
    }
    CustomSheetWrap(state = manageViewModel.blockCardSheetState) {
        Sheet(str = STRING.BLOCK_CARD, hotlist = manageViewModel.blockCardSheetState, onCancel = {
            manageViewModel.blockCardSheetState.value=!manageViewModel.blockCardSheetState.value
        }) {
            manageViewModel.blockCardSheetState.value=!manageViewModel.blockCardSheetState.value
            manageViewModel.blockOtpSheetState.value=true
            manageViewModel.delay.value=1000

        }
        manageViewModel.Otp.value=""
    }
    CustomSheetWrap(state = manageViewModel.viewBalanceOtpSheetState, delay = manageViewModel.delay.value,initOffset = 1000) {

        EnterOTPPinSheet(state = manageViewModel.Otp, oncancel = { manageViewModel.viewBalanceOtpSheetState.value=false


        }){
            cardDataViewModel.viewCardData("181",SDK_CONSTANTS.cardNumber){
                manageViewModel.cardDataMask.value=false
                manageViewModel.viewBalanceOtpSheetState.value=false
                manageViewModel.Otp.value=""
            }
            manageViewModel.Otp.value=""


        }

    }


}



