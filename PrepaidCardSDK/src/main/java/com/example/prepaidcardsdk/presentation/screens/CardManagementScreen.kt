package com.example.prepaidcard.screens

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newui.components.FlipCard

import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomCheckBox
import com.example.prepaidcard.components.CustomCheckField
import com.example.prepaidcard.components.CustomScaffoldScreen
import com.example.prepaidcard.components.CustomTopBar

import com.example.prepaidcard.utils.Destination
import com.example.prepaidcard.utils.FilterOption
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.presentation.viewmodels.GeneratePinViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.ui.theme.Resetcolor
import com.example.prepaidcardsdk.ui.theme.cancelGray
import com.example.prepaidcardsdk.ui.theme.cdback
import com.example.prepaidcardsdk.ui.theme.lighttealGreen
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS
import javax.crypto.spec.DESKeySpec

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardManagementScreen(
    rootNavController: NavHostController,
    viewModel: GeneratePinViewModel,
    onClick: (state: Boolean) -> Unit = {},
    manageViewModel: ManageCardViewModel
) {
    val ReplaceToggleState =
        manageViewModel.ReplaceToggleState


    val CvvToggleState =
        manageViewModel.CvvToggleState_d

    val ResetPinToggleState =
        manageViewModel.ResetPinToggleState_d

    val PauseCardToggleState =
        manageViewModel.PauseCardToggleState_d

    val PauseCardOtpToggleState =
        manageViewModel.PauseCardOtpState

    val HotListToggleState =
        manageViewModel.HotListToggleState_d

    val ResetPinOtpState =
        manageViewModel.ResetPinOtpState

    val DetailsState =
       manageViewModel.DetailsState



    Scaffold(topBar = {
        CustomTopBar {
            rootNavController.popBackStack()
        }
    })
    {
        if(manageViewModel.HotListToggleState.value){
            rootNavController.navigate(Destination.VIEW_CARDS_SCREEN){
                popUpTo(Destination.VIEW_CARDS_SCREEN)
            }
        }
        CustomScaffoldScreen(
            sheet = ResetPinToggleState,
            resetPinOtp = ResetPinOtpState,
            blockCard = PauseCardToggleState,
            blockCardOtp=PauseCardOtpToggleState,
            hotlist = HotListToggleState,
            hotlistCardOtp = manageViewModel.HotListOtpState,
            cvv = CvvToggleState,
            maskState = viewModel.mask,
            details = DetailsState,
            manageViewModel=manageViewModel,
            {
                var editTextValue by remember { mutableStateOf("") }
                var textFieldValue by remember { mutableStateOf("dbehera56@gmail.com") }

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

                        FlipCard(name=SDK_CONSTANTS.cardUser,cardno=SDK_CONSTANTS.cardNumber,exp=SDK_CONSTANTS.expiryDate){
                            manageViewModel.viewBalanceOtp.value=!manageViewModel.viewBalanceOtp.value
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text("CVV:", fontWeight = FontWeight(700))
                            Text(
                                "123",
                                fontWeight = FontWeight(700),
                                modifier = Modifier.blur(manageViewModel.cvvMask.value)
                            )
                            Switch(
                                checked =manageViewModel.CvvToggleState.value,
                                onCheckedChange = {
                                    CvvToggleState.value=it

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
                                    ) { CustomButton(
                                        text = "SUBMIT",
                                        buttonColor = lighttealGreen
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
                        } else if (manageViewModel.clickedState.value == "Managecard") {
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
                                    CustomCheckField(
                                        state = manageViewModel.ResetPinToggleState,
                                        text = "Reset Pin",
                                        res = R.drawable.group_one
                                    ) {
                                        manageViewModel.ResetPinToggleState.value=!manageViewModel.ResetPinToggleState.value

                                        ResetPinToggleState.value =
                                            !ResetPinToggleState.value

                                    }

                                    CustomCheckField(
                                        state = manageViewModel.PauseCardToggleState,
                                        text = "Pause card",
                                        res = R.drawable.group_two
                                    ) {
//                                        manageViewModel.PauseCardToggleState.value=!manageViewModel.PauseCardToggleState.value
                                        PauseCardToggleState.value =
                                            !PauseCardToggleState.value
                                        onClick(PauseCardToggleState.value)
                                    }

                                    CustomCheckField(
                                        state = manageViewModel.HotListToggleState,
                                        text = "Hotlist card",
                                        res = R.drawable.group_three
                                    ) {

                                        HotListToggleState.value = !HotListToggleState.value

                                        onClick(HotListToggleState.value)

                                    }

                                    CustomCheckField(
                                        state = ReplaceToggleState,
                                        text = "Replace card",
                                        res = R.drawable.group_four
                                    ) {
                                        ReplaceToggleState.value = !ReplaceToggleState.value
                                        onClick(ReplaceToggleState.value)
                                    }

                                }
                            }
                        } else if (manageViewModel.clickedState.value == "Details") {
                            DetailsState.value
                            Box(
                                Modifier
                                    .padding(
                                        vertical = 10.dp,
                                        horizontal = 10.dp
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
                                            horizontal = 20.dp,
                                            vertical = 10.dp
                                        ), colors = CardDefaults.elevatedCardColors(cdback),
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
                                                        fontSize = 14.sp),
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
                                                        fontSize = 14.sp),
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
                                                        fontSize = 14.sp),
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
                            Box(
                                Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "ComingSoon...",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight(700),
                                    fontFamily = FontFamily(
                                        listOf(Font(R.font.lato_bold))
                                    ),
                                    color = Color.Gray,
                                )
                            }
                        }
                    }
                }
            },
        )

    }
}