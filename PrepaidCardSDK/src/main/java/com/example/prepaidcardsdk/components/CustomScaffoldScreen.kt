package com.example.prepaidcard.components

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.prepaidcard.utils.STRING
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffoldScreen(
    sheet: MutableState<Boolean>,
    enterOtp: MutableState<Boolean>,
    pauseCard: MutableState<Boolean>,
    hotlist: MutableState<Boolean>,
    cvv: MutableState<Boolean>,
    maskState: MutableState<Dp>,
    details: MutableState<Boolean>,
    manageViewModel: ManageCardViewModel,
    mainContent: @Composable () -> Unit,


) {
    val otpInp = remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    Box(
        Modifier.blur(
            if (sheet.value || enterOtp.value || pauseCard.value || hotlist.value || cvv.value) {
                10.dp
            } else {
                0.dp
            }
        )
    ) {

        mainContent()
        if (sheet.value || enterOtp.value || pauseCard.value || hotlist.value || cvv.value) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Color.Black.copy(0.5f)

                    )
                    .pointerInput(null, null, {})
            ) {}
        }


    }
//    @Composable
//    fun CustomSheetWrap(state: MutableState<Boolean>, cont: @Composable () -> Unit) {
//        Box(
//            Modifier.fillMaxSize()
//        ) {
//            AnimatedVisibility(visible = state.value,
//
//                enter = slideInVertically(animationSpec = tween(1000,if() easing = EaseInOut),
//                    initialOffsetY = { 500 }),
//                exit = slideOutVertically(tween(1000, easing = EaseInOut), targetOffsetY = { 500 }),
//                modifier = Modifier.align(Alignment.BottomStart)) {
//                Card(
//                    Modifier
//                        .fillMaxWidth()
//                        .background(White),
//                    colors = CardDefaults.cardColors(containerColor = White),
//                    shape = RoundedCornerShape(10.dp, topEnd = 10.dp, 0.dp, 0.dp)
//                ) {
//
//                    cont()
//
//                }
//
//
//            }
//        }
//    }

    @Composable
    fun Sheet(str: String, hotlist: MutableState<Boolean>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                str, textAlign = TextAlign.Center, fontSize = 16.sp
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(30.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Button(
                    onClick = {
                        hotlist.value = !hotlist.value
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .width(156.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)

                ) {
                    Text(text = "Yes", fontSize = 14.sp)
                }
                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .width(156.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFA09D9D))
                ) {
                    Text("No")
                }
            }


        }
    }

    @Composable
    fun ResetPinSheet(hotlist: MutableState<Boolean>) {
    var isError= remember {
        mutableStateOf(false)
    }
        var errStr= remember {
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
                if(isError.value){
                    Text(errStr.value,color=Red, fontWeight = FontWeight(700))
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
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
                    onValueChange = { manageViewModel.reenterPin.value = it
                        if(it.length==4&&!it.matches(manageViewModel.enterPin.value.toRegex())){
                            isError.value=true
                            errStr.value="PINS DONT MATCH"
                        }
                        else{
                            isError.value=false
                        }

                                    },
                    shape = RoundedCornerShape(2.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
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
                        if(manageViewModel.enterPin.value.isEmpty()||manageViewModel.reenterPin.value.isEmpty()){
                            isError.value=true
                            errStr.value="FIELDS CANT BE EMPTY"
                        }
                        else{
                            isError.value=false
                        }



                        hotlist.value = !hotlist.value
                        enterOtp.value = !enterOtp.value
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

    val txt = remember {
        mutableStateOf("")
    }
    val timer = object : CountDownTimer(30000, 1000) {
        override fun onTick(p0: Long) {
            txt.value = (p0 / 1000).toString()
        }

        override fun onFinish() {
            txt.value = "Time finished"
        }

    }
    if (enterOtp.value) timer.start() else timer.cancel()

    @Composable
    fun EnterOTPPinSheet(hotlist: MutableState<Boolean>, maskState: MutableState<Dp>) {

        var textFieldSize by remember { mutableStateOf(Size.Zero) }
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
                val comt = LocalContext.current
                Text(
                    text = "Enter OTP",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
                BasicTextField(
                    value = manageViewModel.resetPinOtp.value,
                    onValueChange = {

                        if (it.length <= 4) {
                           manageViewModel.resetPinOtp.value= it
                        }


                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.NumberPassword
                    )
                )

                {

                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        repeat(4) {

                            val char = when {
                                it >= manageViewModel.resetPinOtp .value.length -> ""
                                else -> manageViewModel.resetPinOtp .value[it]
                            }


                            Card(
                                Modifier
                                    .padding(2.dp)
                                    .size(50.dp),
                                colors = CardDefaults.cardColors(LightGray),
                                shape = RoundedCornerShape(5.dp),

                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {

                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                                    Text(
                                        char.toString(), textAlign = TextAlign.Center, color = Black, fontWeight = FontWeight(600)
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
                    onClick = {
                        manageViewModel.resetPin(){
                                Toast.makeText(context,it.statusDesc,Toast.LENGTH_LONG).show()
                        }
                        hotlist.value = !hotlist.value
                        maskState.value = if (maskState.value == 0.dp) {
                            10.dp
                        } else {
                            0.dp
                        }
                    },
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


            Text("Remaining Time: ${txt.value}")


        }
    }

    @Composable
    fun CustomSheetWrap(
        state: MutableState<Boolean>,
        initOffset: Int = 500,
        cont: @Composable () -> Unit
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            AnimatedVisibility(
                visible = state.value,

                enter = slideInVertically(animationSpec = tween(
                    1000,
                    delayMillis = if (state == enterOtp) 1000 else 0,
                    easing = EaseInOut
                ),
                    initialOffsetY = { initOffset }),
                exit = slideOutVertically(
                    tween(1000, easing = EaseInOut),
                    targetOffsetY = { initOffset }),
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Card(
                    Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = White),
                    shape = RoundedCornerShape(50.dp, topEnd = 50.dp, 0.dp, 0.dp)

                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp), contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth(0.4f)
                                .height(3.dp)
                                .background(
                                    Gray,
                                    RoundedCornerShape(5.dp)
                                )
                        )
                    }
                    cont()

                }


            }
        }
    }

    CustomSheetWrap(state = sheet, 1000) {
        ResetPinSheet(hotlist = sheet)
    }

    CustomSheetWrap(state = hotlist) {
        Sheet(str = STRING.HOTLIST_CARD, hotlist = hotlist)
    }

    CustomSheetWrap(state = enterOtp, 800) {
        EnterOTPPinSheet(hotlist = enterOtp, maskState)
    }
    CustomSheetWrap(state = cvv, 800) {
        EnterOTPPinSheet(hotlist = cvv, maskState)
    }
    CustomSheetWrap(state = details, 800) {
        EnterOTPPinSheet(hotlist = details, maskState)
    }

    CustomSheetWrap(state = pauseCard) {
        Sheet(STRING.PAUSE_CARD, pauseCard)
    }


}


