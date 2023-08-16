package com.example.prepaidcard.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.newui.components.FlipCard
import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomCheckBox
import com.example.prepaidcard.components.CustomCheckField
import com.example.prepaidcard.components.CustomScaffoldScreen
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.ui.theme.ColorReset
import com.example.prepaidcard.ui.theme.Cultured
import com.example.prepaidcard.ui.theme.HitextColor
import com.example.prepaidcard.ui.theme.cancelGray
import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcard.utils.FilterOption
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageTen(rootNavController: NavHostController, onClick: (state:Boolean) -> Unit = {}) {
    val ReplaceToggleState = remember {
        mutableStateOf(false)
    }
    val CvvToggleState = remember {
        mutableStateOf(false)
    }
    val ResetPinToggleState = remember {
        mutableStateOf(false)
    }
    val PauseCardToggleState = remember {
        mutableStateOf(false)
    }
    val HotListToggleState = remember {
        mutableStateOf(false)
    }
    val EnterOTPToggleState = remember {
        mutableStateOf(false)
    }
   @Composable
    fun Sheet() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Are you sure ? You want to cancel your prepaid card",
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
                        ResetPinToggleState.value=!ResetPinToggleState.value
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .width(156.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Cyan)

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
    Scaffold(topBar = {  CustomTopBar {
        rootNavController.popBackStack()
    }})
    {
    CustomScaffoldScreen(sheet =  ResetPinToggleState, enterOtp = EnterOTPToggleState, hotlist = HotListToggleState, pauseCard =  PauseCardToggleState, cvv = CvvToggleState)
    {



        var textFieldSize by remember { mutableStateOf(Size.Zero) }

        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .verticalScroll(enabled = true, state = ScrollState(0))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                FlipCard()
                Row(verticalAlignment = Alignment.CenterVertically) {
                   
                    Text("CVV****", fontWeight = FontWeight(700))
                    Switch(
                        checked = CvvToggleState.value,
                        onCheckedChange = {
                            CvvToggleState.value = !CvvToggleState.value
                            onClick(CvvToggleState.value)
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = ColorReset,
                            uncheckedIconColor = ColorReset,
                            uncheckedBorderColor = ColorReset,
                            disabledUncheckedIconColor = ColorReset
                        ),
                    )

                }
                val list = listOf<String>("LoadCard", "Managecard", "Statement", "Details")
                val clickedState = remember {
                    mutableStateOf("")
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    list.forEachIndexed { index, s ->
                        Text(
                            s,
                            Modifier.clickable { clickedState.value = s },
                            color = if (s != clickedState.value) ColorReset else {
                                Color(0xFFDB8726)
                            },
                            fontWeight = FontWeight(600)
                        )
                        if (index != list.size - 1) {
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(1.dp)
                                    .background(color = ColorReset)
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

                if (clickedState.value == "Statement") {
                    Box(
                        Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .fillMaxSize()
                    ) {
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                "Card Statement", fontWeight = FontWeight(600),
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
                                    text = "SUBMIT",
                                    buttonColor = lighttealGreen
                                ) {
                                    if(checkBoxState.value=="Last 10 Transaction"){
                                        rootNavController.navigate(Destination.SCREEN_TWENTY_SIX+"/none")
                                    }
                                    else{
                                        rootNavController.navigate(Destination.SCREEN_TWENTY_SIX+"/${FilterOption.SelectDate}")
                                    }
                                }
                                CustomButton(text = "CANCEL", buttonColor = cancelGray) {
                                    rootNavController.popBackStack()
                                }
                            }

                        }
                    }
                }
                else if (clickedState.value == "Managecard") {
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
                            CustomCheckField(state = ResetPinToggleState, text = "Reset Pin", res =R.drawable.group_one ) {
                                ResetPinToggleState.value = !ResetPinToggleState.value
                                onClick(ResetPinToggleState.value)
                            }

                            CustomCheckField(state = PauseCardToggleState, text = "Pause card", res = R.drawable.group_two) {
                                PauseCardToggleState.value = !PauseCardToggleState.value
                                onClick(PauseCardToggleState.value)
                            }

                            CustomCheckField(state = HotListToggleState, text = "Hotlist card", res = R.drawable.group_three) {
                                HotListToggleState.value = !HotListToggleState.value
                                onClick(HotListToggleState.value)

                            }

                            CustomCheckField(state = ReplaceToggleState, text = "Replace card", res =R.drawable.group_four ) {
                                ReplaceToggleState.value = !ReplaceToggleState.value
                                onClick(ReplaceToggleState.value)
                            }

                        }
                    }
                }

                else{
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text("ComingSoon...", fontSize = 30.sp, fontWeight = FontWeight(700), fontFamily = FontFamily(
                            listOf(Font(R.font.lato_bold))
                        ), color = Color.Gray,
                        )
                    }
                }
                }
            }
        }
    }}