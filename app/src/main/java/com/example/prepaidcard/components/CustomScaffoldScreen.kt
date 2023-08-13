package com.example.prepaidcard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.aepssdk.presenter.common.components.InputTextField
import com.example.prepaidcard.R
import com.example.prepaidcard.ui.theme.Cultured
import com.example.prepaidcard.utils.STRING


@Composable
fun CustomScaffoldScreen (
    sheet: MutableState<Boolean>,
    enterOtp:MutableState<Boolean>,
    pauseCard:MutableState<Boolean>,
    hotlist:MutableState<Boolean> ,
    mainContent:@Composable ()->Unit) {
    val otpInp = remember {
        mutableStateOf("0000")
    }

    mainContent()
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
                str,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
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

                    }, shape = RoundedCornerShape(5.dp),
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
        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
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
                Row {
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                }
                Text(
                    text = "Renter Generate PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
                Row {
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                    OutlinedTextField(
                        value = "", enabled = false, readOnly = true, onValueChange = {},
                        modifier = Modifier
                            .width(70.dp)
                            .padding(5.dp)
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize()
                            },
                        placeholder = { Text(text = "") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Cultured,
                            focusedBorderColor = Cultured,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Cultured
                        )
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Button(
                    onClick = {
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
                    Text(text = "Yes", fontSize = 14.sp)
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(5.dp),
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
    fun EnterOTPPinSheet(hotlist: MutableState<Boolean>) {
        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Enter Generate PIN",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
                BasicTextField(value = otpInp.value, onValueChange = {

                        otpInp.value = it


                }) {Row() {


                    for (i in 0..otpInp.value.length-1) {

                        Card(
                            Modifier
                                .padding(5.dp)
                                .size(50.dp),
                            colors = CardDefaults.cardColors(White),
                            shape= RoundedCornerShape(1.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Text("jh" )
                        }
                    }
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
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

                    }, shape = RoundedCornerShape(5.dp),
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

    AnimatedVisibility(
        visible = sheet.value,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Black.copy(0.5f))
                .pointerInput(null, { Unit },)
        ) {

        }
    }
    Box(
        Modifier
            .fillMaxSize()
    ) {


        AnimatedVisibility(visible = sheet.value,
            enter = slideInVertically(
                animationSpec = tween(1000, easing = EaseInOut),
                initialOffsetY = { 1000 }),
            exit = slideOutVertically(
                tween(1000, easing = EaseInOut), targetOffsetY = { 1000 }
            ),
            modifier = Modifier.align(Alignment.BottomStart)) {
            Card(
                Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp, topEnd = 10.dp, 0.dp, 0.dp)
            ) {

                ResetPinSheet(hotlist = sheet)

            }


        }

    }


    AnimatedVisibility(
        visible = enterOtp.value,
        enter = fadeIn(animationSpec = tween(200)),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Black.copy(0.5f))
                .pointerInput(null, { Unit },)
        ) {

        }
    }
    Box(
        Modifier
            .fillMaxSize()
    ) {
        AnimatedVisibility(visible = enterOtp.value,
            enter = slideInVertically(
                animationSpec = tween(
                    1000,
                    delayMillis = 1000,
                    easing = EaseInOut
                ), initialOffsetY = { 1000 }),
            exit = slideOutVertically(
                tween(1000, easing = EaseInOut), targetOffsetY = { 1000 }
            ),
            modifier = Modifier.align(Alignment.BottomStart)) {
            Card(
                Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp, topEnd = 10.dp, 0.dp, 0.dp)
            ) {

                EnterOTPPinSheet(hotlist = enterOtp)

            }


        }


    }


    AnimatedVisibility(
        visible = pauseCard.value,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Black.copy(0.5f))
                .pointerInput(null, { Unit },)
        ) {

        }
    }
    Box(
        Modifier
            .fillMaxSize()
    ) {

        AnimatedVisibility(visible = pauseCard.value,
            enter = slideInVertically(
                animationSpec = tween(1000, easing = EaseInOut),
                initialOffsetY = { 500 }),
            exit = slideOutVertically(
                tween(1000, easing = EaseInOut), targetOffsetY = { 500 }
            ),
            modifier = Modifier.align(Alignment.BottomStart)) {
            Card(
                Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp, topEnd = 10.dp, 0.dp, 0.dp)
            ) {

                Sheet(STRING.PAUSE_CARD, pauseCard)

            }


        }

    }



    AnimatedVisibility(
        visible = hotlist.value,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Black.copy(0.5f))
                .pointerInput(null, { Unit },)
        ) {

        }
    }
    Box(
        Modifier
            .fillMaxSize()
    ) {
        AnimatedVisibility(visible = hotlist.value,
            enter = slideInVertically(
                animationSpec = tween(1000, easing = EaseInOut),
                initialOffsetY = { 500 }),
            exit = slideOutVertically(
                tween(1000, easing = EaseInOut), targetOffsetY = { 500 }
            ),
            modifier = Modifier.align(Alignment.BottomStart)) {
            Card(
                Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp, topEnd = 10.dp, 0.dp, 0.dp)
            ) {

                Sheet(STRING.HOTLIST_CARD, hotlist)

            }


        }
    }



}
