package com.example.prepaidcardsdk.screens

import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomCheckBox
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.components.CustomAlertDialog
import com.example.prepaidcardsdk.components.CustomBlockedAlertDialog
import com.example.prepaidcardsdk.components.CustomLoader
import com.example.prepaidcardsdk.data.model.resp.toViewcardresponseWrapperDomain
import com.example.prepaidcardsdk.presentation.viewmodels.CardDataViewModel
import com.example.prepaidcardsdk.ui.theme.HitextColor
import com.example.prepaidcardsdk.ui.theme.cancelGray
import com.example.prepaidcardsdk.ui.theme.cdback
import com.example.prepaidcardsdk.ui.theme.finocolor
import com.example.prepaidcardsdk.ui.theme.gray_color
import com.example.prepaidcardsdk.ui.theme.isuGreen
import com.example.prepaidcardsdk.ui.theme.isuOrrange
import com.example.prepaidcardsdk.ui.theme.light_finocolor
//import com.example.prepaidcardsdk.ui.theme.light_isuOrrange
import com.example.prepaidcardsdk.ui.theme.lighttealGreen
import com.example.prepaidcardsdk.ui.theme.neon
import com.example.prepaidcardsdk.ui.theme.tealGreen
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun ViewCardsScreen(rootNavController: NavHostController, viewModel: CardDataViewModel)
{
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    BackHandler {
        activity.finish()
    }
Scaffold(topBar = { CustomTopBar {
    activity.finish()
}}) {


    Column(
        Modifier
            .padding(it)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .verticalScroll(enabled = true, state = ScrollState(0))

    ) {
        if (viewModel.isBlocked.value) {
            AlertDialog(onDismissRequest = { /*TODO*/ }) {
                CustomBlockedAlertDialog(errMsg = viewModel.errorMessage.value, onCancel = {
                    viewModel.isBlocked.value = false
                    viewModel.isError.value = false
                    viewModel.errorMessage.value = ""
                })
                {

                    rootNavController.navigate(Destination.ENTER_OTP_SCREEN) {


                    }
                    viewModel.isBlocked.value = false
                    viewModel.isError.value = false
                    viewModel.errorMessage.value = ""
                }
            }
        } else if (viewModel.isError.value) {

            AlertDialog(onDismissRequest = { /*TODO*/ }) {
                CustomAlertDialog(errMsg = viewModel.errorMessage.value) {
                    viewModel.isError.value = false
                    if (viewModel.destination.value.isNotEmpty()) {
                        rootNavController.navigate(Destination.ENTER_MOBILE_NUM_SCREEN)
                        viewModel.destination.value = ""
                    }
                }
            }

        } else if (viewModel.isLoading.value) {
            AlertDialog(onDismissRequest = { /*TODO*/ }) {
                CustomLoader()
            }


        }
        @Composable
        fun CustomAlertDialogBox(error: MutableState<Boolean>, errMsg: String, dest: String) {
            AlertDialog(onDismissRequest = { }) {
                Card(Modifier.size(300.dp)) {
                    Box(Modifier.fillMaxSize()) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(errMsg)
                            Button(onClick = {
                                error.value = false
                                rootNavController.navigate(dest)

                            }) {
                                Text("ok")

                            }
                        }

                    }

                }

            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row {
                Text(
                    text = "Hi ,",
                    color = HitextColor,
                    fontFamily = FontFamily(Font(R.font.roboto_medium_italic)),
                    fontSize = 22.sp
                )
                Text(
                    text = "${
                        if (viewModel.cardList.value?.isNotEmpty() == true) {
                            viewModel.cardList.value?.get(0)?.nameonCard
                        } else ""
                    }!",
                    color = HitextColor,
                    fontFamily = FontFamily(Font(R.font.robot_medium)),
                    fontSize = 22.sp
                )
            }

        }


        LaunchedEffect(key1 = true) {
            if (viewModel.cardList.value?.isEmpty() == true) {
                viewModel.cardDataByCustomer()
            }
        }

        viewModel.cardList.value?.let {

            if (it.isNotEmpty()) {
                LazyRow {
                    items(it) {
                        if (it.cardType.equals("GPR", true)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.outlinedCardElevation(14.dp),
                                colors = CardDefaults.cardColors(
                                    Color.White
                                ),
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(180.dp)
                                        .width(300.dp)
                                        .clickable(onClick = {
                                            if (it != null) {
                                                SDK_CONSTANTS.cardRefId = it.cardRefId
                                            }
                                            it.cardRefId?.let { it1 ->
                                                viewModel.viewCardData(
                                                    cardRefId = it1,
                                                    customerId = "181"
                                                )

                                                { res ->

                                                    if (res != null) {
                                                        if (res.status == "0") {
                                                            //hotlist
                                                            val hotlist =
                                                                res.viewcardresponseWrapper.isHotlist
                                                            val isBlock =
                                                                res.viewcardresponseWrapper.isBlock
                                                            val isActive =
                                                                res.viewcardresponseWrapper.isActive
                                                            //
                                                            if (!hotlist) {
                                                                if (isBlock) {
                                                                    viewModel.isBlocked.value =
                                                                        true
                                                                    viewModel.destination.value =
                                                                        Destination.ENTER_OTP_SCREEN
//                                                                        viewModel.isError.value =
//                                                                            true
                                                                    viewModel.errorMessage.value =
                                                                        "Card is Blocked"
                                                                    viewModel.destination.value =
                                                                        Destination.ENTER_OTP_SCREEN


                                                                } else {
                                                                    if (isActive) {

                                                                        rootNavController.navigate(
                                                                            Destination.CARD_MANAGEMENT_SCREEN
                                                                        )
                                                                    } else {
                                                                        viewModel.isError.value =
                                                                            true
                                                                        viewModel.errorMessage.value =
                                                                            "Card is Inactive"
                                                                        viewModel.destination.value =
                                                                            Destination.CARD_ACTIVATION_SCREEN

                                                                    }
                                                                }
                                                            } else {

                                                                viewModel.isError.value = true
                                                                viewModel.errorMessage.value =
                                                                    "Card is Hot Listed"


                                                            }

                                                        } else {

                                                            viewModel.isError.value = true
                                                            viewModel.errorMessage.value =
                                                                "Card is Hot Listed"
                                                            viewModel.destination.value =
                                                                Destination.VIEW_CARDS_SCREEN
                                                        }
                                                    }
                                                }
                                            }
                                        })
                                ) {
                                    Column(
                                        Modifier.padding(20.dp),
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {

                                        Text(
                                            text = "Prepaid Card",
                                            fontFamily = FontFamily(Font(R.font.robot_medium)),
                                            fontSize = 18.sp
                                        )
                                        it.toViewcardresponseWrapperDomain().decryptedCard?.let { it1 ->
                                            Text(
                                                text = it1.replaceRange(
                                                    0,
                                                    12,
                                                    "xxxx-xxxx-xxxx-"
                                                ), color = gray_color,
                                                modifier = Modifier.blur(0.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(40.dp))
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxWidth(),

                                            verticalAlignment = Alignment.Bottom
                                        ) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.bankitt),
                                                contentDescription = "",
                                                tint = isuGreen
                                            )
                                            Text(
                                                text = if (it.isVirtual) "Virtual" else "Physical",
                                                color = Color.LightGray,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                LazyRow {
                    items(it) {
                        if (it.cardType.equals("GIFT", true)) {
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.outlinedCardElevation(14.dp),
                                colors = CardDefaults.cardColors(
                                    light_finocolor
                                ),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable(onClick = {
                                        if (it != null) {
                                            SDK_CONSTANTS.cardRefId = it.cardRefId
                                        }

                                        it.cardRefId?.let { it1 ->
                                            viewModel.viewCardData(
                                                cardRefId = it1,
                                                customerId = "181"
                                            )
                                            { res ->

                                                if (res != null) {
                                                    if (res.status == "0") {
                                                        //hotlist
                                                        val hotlist =
                                                            res.viewcardresponseWrapper.isHotlist
                                                        val isBlock =
                                                            res.viewcardresponseWrapper.isBlock
                                                        val isActive =
                                                            res.viewcardresponseWrapper.isActive

                                                        if (!hotlist) {
                                                            if (isBlock) {
                                                                viewModel.isBlocked.value =
                                                                    true
                                                                viewModel.destination.value =
                                                                    Destination.ENTER_OTP_SCREEN
//                                                                        viewModel.isError.value =
//                                                                            true
                                                                viewModel.errorMessage.value =
                                                                    "Card is Blocked"
                                                                viewModel.destination.value =
                                                                    Destination.ENTER_OTP_SCREEN


                                                            } else {
                                                                if (isActive) {
                                                                    rootNavController.navigate(
                                                                        Destination.CARD_MANAGEMENT_SCREEN
                                                                    )
                                                                } else {
                                                                    viewModel.isError.value =
                                                                        true
                                                                    viewModel.errorMessage.value =
                                                                        "Card is Inactive"
                                                                    viewModel.destination.value =
                                                                        Destination.CARD_ACTIVATION_SCREEN

                                                                }
                                                            }
                                                        } else {

                                                            viewModel.isError.value = true
                                                            viewModel.errorMessage.value =
                                                                "Card is Hot Listed"
                                                            viewModel.destination.value =
                                                                ""
                                                        }

                                                    } else {

                                                        viewModel.isError.value = true
                                                        viewModel.errorMessage.value =
                                                            "Card is Hot Listed"
                                                        viewModel.destination.value =
                                                            Destination.VIEW_CARDS_SCREEN
                                                    }
                                                }
                                            }
                                        }
                                    })
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(180.dp)
                                        .width(300.dp)
                                ) {
                                    Column(
                                        Modifier.padding(20.dp),
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Text(
                                            text = "Gift Card",
                                            fontFamily = FontFamily(Font(R.font.robot_medium)),
                                            fontSize = 18.sp,
                                            color= Color.White
                                        )

                                        it.toViewcardresponseWrapperDomain().decryptedCard?.let { it1 ->
                                            Text(
                                                text = it1.replaceRange(
                                                    0,
                                                    12,
                                                    "xxxx-xxxxx-xxxxx-"
                                                ),
                                                color = Color.LightGray,
                                                modifier = Modifier.blur(0.dp),
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(40.dp))
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {


                                            Icon(
                                                painter = painterResource(id = R.drawable.bankitt),
                                                contentDescription = "",
                                                tint = Color.White
                                            )
                                            Text(
                                                text = if (it.isVirtual) "Virtual" else "Physical",
                                                color = Color.LightGray,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                                            )
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
//            Column {
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(5.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "My Payments",
//                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.forwardspace),
//                        contentDescription = "Back"
//                    )
//                }
//                Row(
//                    Modifier
//                        .padding(5.dp)
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "Recent",
//                        fontFamily = FontFamily(Font(R.font.robot_medium)),
//                        fontSize = 14.sp
//                    )
//                    Text(
//                        text = "Received",
//                        fontFamily = FontFamily(Font(R.font.robot_medium)),
//                        fontSize = 14.sp,
//                        color = gray_color
//                    )
//                    Text(
//                        text = "Pay",
//                        fontFamily = FontFamily(Font(R.font.robot_medium)),
//                        fontSize = 14.sp
//                    )
//                }
//                Column(
//                    Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.girl_doing_online_payment),
//                        contentDescription = "Girl doing online payment",
//                        colorFilter = ColorFilter.tint(
//                            gray_color
//                        )
//                    )
//                        Text(
//                            text = "Last 5 Transaction you should view",
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            fontSize = 14.sp,
//                            color = gray_color
//                        )
//
//                        if (SDK_CONSTANTS.kycType == "min_kyc") {
//                            Text(text = "Your KYC is Pending, Please complete your KYC and proceed further", fontWeight = FontWeight.ExtraBold, modifier = Modifier
//                                .background(color = Color.Yellow.copy(0.1f))
//                                .basicMarquee(
//                                    animationMode = MarqueeAnimationMode.Immediately,
//                                    delayMillis = 0
//                                ), color = neon)
//                            Button(onClick = { rootNavController.navigate(Destination.KYC_SCREEN) }) {
//                               Text(text = "KYC SCREEN")
//                        }
//                    }
//                }
//            }
        Column()


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
                        "User Details",
                        fontWeight = FontWeight(600),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.lato_bold))
                    )
                    ElevatedCard(
                        modifier = Modifier.padding(
                            horizontal = 0.dp, vertical = 10.dp
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
                                    value = "Karthik@gmail.com",

                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Done
                                    ),
                                    textStyle = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        fontSize = 14.sp
                                    ),
                                    onValueChange = {

                                    },
//                                            modifier = Modifier.blur(if(manageViewModel.cardDataMask.value)10.dp else 0.dp)
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
//                                            modifier = Modifier.blur(viewModel.mask.value)
                                )
                            }
//                                    Row(
//                                        Modifier.fillMaxWidth(),
//                                        horizontalArrangement = Arrangement.SpaceBetween
//                                    ) {
//                                        Text(
//                                            text = "Monthly Limit:",
//                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                            fontSize = 12.sp
//                                        )
//
//                                        BasicTextField(
//                                            value = viewModel.monthlyLimit.value,
//                                            enabled = viewModel.mask.value != 10.dp,
//                                            keyboardOptions = KeyboardOptions(
//                                                keyboardType = KeyboardType.Number,
//                                                imeAction = ImeAction.Done
//                                            ),
//                                            textStyle = TextStyle(
//                                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                                fontSize = 14.sp
//                                            ),
//                                            onValueChange = {
//                                                viewModel.monthlyLimit.value = it
//                                            },
//                                            modifier = Modifier.blur(viewModel.mask.value)
//                                        )
//                                    }
//                                    Row(
//                                        Modifier.fillMaxWidth(),
//                                        horizontalArrangement = Arrangement.SpaceBetween
//                                    ) {
//                                        Text(
//                                            text = "Card Limit:",
//                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                            fontSize = 12.sp
//                                        )
//
//                                        BasicTextField(
//                                            value = viewModel.cardLimit.value,
//                                            enabled = viewModel.mask.value != 10.dp,
//                                            keyboardOptions = KeyboardOptions(
//                                                keyboardType = KeyboardType.Number,
//                                                imeAction = ImeAction.Done
//                                            ),
//                                            textStyle = TextStyle(
//                                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                                fontSize = 14.sp
//                                            ),
//                                            onValueChange = {
//                                                viewModel.cardLimit.value = it
//                                            },
//                                            modifier = Modifier.blur(viewModel.mask.value)
//                                        )
//                                    }
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
//                                            modifier = Modifier.blur(viewModel.mask.value)
                                )
                            }

                        }

                    }



                    if (SDK_CONSTANTS.kycType == "min_kyc") {
                        Text(text = "Your KYC is Pending, Please complete your KYC and proceed further", fontWeight = FontWeight.ExtraBold, modifier = Modifier
                            .background(color = cdback)
                            .basicMarquee(
                                animationMode = MarqueeAnimationMode.Immediately,
                                delayMillis = 0
                            ), color = finocolor)
                        Row(Modifier.fillMaxWidth().height(100.dp), horizontalArrangement = Arrangement.Center){
                        ElevatedButton(onClick = { rootNavController.navigate(Destination.KYC_SCREEN) }, modifier = Modifier
                            .size(70.dp)
                            .background(
                                Color.Transparent,
                                RoundedCornerShape(50.dp)
                            ), colors = ButtonDefaults.buttonColors(finocolor), elevation = ButtonDefaults.buttonElevation(10.dp)) {
                            Icon(painter = painterResource(id = R.drawable.kyc), contentDescription = "",tint= Color.White)
                        }
                    }}
                }
            }
        }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    CustomButton(
//                        text = "SUBMIT", buttonColor = finocolor
//                    ) {
//
//                    }
//                    CustomButton(text = "CANCEL", buttonColor = cancelGray) {
//                        rootNavController.popBackStack()
//                    }
//
//                }}
    }
}}







