package com.example.newui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prepaidcardsdk.domain.usecases.ResetPinUseCase
import com.example.prepaidcardsdk.presentation.viewmodels.GeneratePinViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.ui.theme.HitextColor
import com.example.prepaidcardsdk.ui.theme.Resetcolor
import com.example.prepaidcardsdk.ui.theme.tealGreen
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS


enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

@Composable
fun FlipCard(
    name: String,
    cardno: String,
    exp: String,
    avlbaln: String,
    cardfaceState: MutableState<CardFace>,
    startanim:MutableState<Boolean>,
    viewBalance: () -> Unit,
) {


//    if (viewModel != null) {
//        if(viewModel.cvvValue.value.isNotEmpty()){
//            cardfaceState.value = cardfaceState.value.next
//        }
//    }

    val rotate= animateFloatAsState(
        targetValue = if(startanim.value)0f else 90f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseInOut,
        )
    )
    val scale= animateFloatAsState(
        targetValue = if(startanim.value)1f else 1.3f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseInOut,
        )
    )
    val translate= animateFloatAsState(
        targetValue = if(startanim.value)0f else 500f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseInOut,
        )
    )

    val rotatiom = animateFloatAsState(
        targetValue = cardfaceState.value.angle,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseInOut,
        )
    )
    val cont = LocalContext.current

    Card(
        modifier = Modifier.zIndex(100f)
            .rotate(rotate.value)
            .scale(scale.value)
            .graphicsLayer {
                translationX =translate.value
            },
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {

        if (cardfaceState.value.angle < 90f) {
            AnimatedVisibility(
                visible = cardfaceState.value.angle < 90f,
                modifier = Modifier.padding(0.dp)
            ) {
                PrepaidCard(
                    Modifier
                        .graphicsLayer {
                            rotationY = rotatiom.value

                        }
                        .clickable {


                        },
                    cardno,
                    name,
                    exp,
                    avlbaln,
                    manageCardViewModel = hiltViewModel<ManageCardViewModel>(),
                    generatePinViewModel = hiltViewModel<GeneratePinViewModel>()
                ) {
                    viewBalance()

                }
            }

        } else {
            PrepaidCardBack(Modifier
                .graphicsLayer {
                    rotationY = rotatiom.value
                }
                .clickable {
                    cardfaceState.value = cardfaceState.value.next


                }, manageCardViewModel = hiltViewModel<ManageCardViewModel>
                ()
            )
        }


    }


}


@Composable
fun PrepaidCard(
    clickable: Modifier,
    cardno: String,
    name: String,
    exp: String,
    avlbaln: String,
    manageCardViewModel: ManageCardViewModel,
    generatePinViewModel: GeneratePinViewModel,
    viewBalance: () -> Unit,
) {
    val mask = generatePinViewModel.mask
    var buttonText by remember {
        mutableStateOf("View Details")
    }
    var context = LocalContext.current



    Box(

//        colors = CardDefaults.cardColors(Resetcolor),
        modifier = clickable


            .width(940.dp)
            .height(290.dp)
            .padding(5.dp)
            .drawBehind {

            }.background(Color.Transparent,
                RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center


    ) {


//            Canvas(modifier = Modifier
//                .fillMaxWidth()
//                .height(250.dp))
//            {
//
//                drawCircle(
//                    brush = Brush.horizontalGradient(listOf(  Resetcolor, tealGreen), startX = 0f, endX = 700f),
//
//                    radius = 900f,
//                    center = Offset(x = 500f, y = 1150f)
//                )
//            }
        Image(
            modifier = Modifier
                .width(1200.dp)
                .height(280.dp).background(Color.Transparent,
                    RoundedCornerShape(10.dp)
                ),


            painter = painterResource(id = if(SDK_CONSTANTS.cardType.equals("GIFT",true) ) com.example.prepaidcardsdk.R.drawable.giftcard else com.example.prepaidcardsdk.R.drawable.prepaidcrop),

            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )


        Card(
            modifier = Modifier
                .width(640.dp)
                .height(290.dp),
            colors = CardDefaults.cardColors(Color.Transparent)
        ) {
//        Row(
//            Modifier
//
//                .padding(0.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
////           Icon(painter = painterResource(id = R.drawable.ic_launcher_background),"", tint = Color.White )
//
//            Column() {
//                Text("NSDL", color = Color.White)
//                Text("PaymentsBank", color = Color.White, fontWeight = FontWeight(700))
//            }
//
//            Text(text = "Valid in India Only", color = Color.Black,)
//
//        }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp), horizontalArrangement = Arrangement.End){
//                    Text("${SDK_CONSTANTS.cardType}", color = Resetcolor, fontWeight = FontWeight(700))
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedButton(
                    onClick = {
                        manageCardViewModel.viewBalanceOtpSheetState.value = true
//                        if (buttonText == "View Details") {
//                            buttonText = "Available Balance:\n ${avlbaln}"
//                        }else{
//                            buttonText = "View Details"
//                        }
//                        mask.value=if(mask.value==0.dp){
//                            10.dp
//                        }else{
//                            0.dp
//                        }
                    },
                    border = BorderStroke(1.dp, Color.White),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.LightGray.copy(0.3f)
                    )
//                    enabled = manageCardViewModel.CvvToggleState.value
                ) {
                    Text(
                        buttonText,
                        color = Resetcolor,
                        fontWeight = FontWeight(700),
                        fontFamily = FontFamily.Monospace
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                ) {
                    Column() {
                        Text(
                            if (manageCardViewModel.cardDataMask.value || SDK_CONSTANTS.cardUser.isEmpty()) name.replaceRange(
                                0,
                                name.length,
                                "XXXXX"
                            ) else name,
                            color = Resetcolor,
                            style = TextStyle(letterSpacing = 5.sp, fontWeight = FontWeight(800))
                        )
                        Spacer(modifier = Modifier.height(5.dp))
//                    Text(if(manageCardViewModel.cardDataMask.value||SDK_CONSTANTS.cardNumber.isEmpty())"${cardFormat(cardno)}" else cardSpaceFormat(cardno), color = Color.Black, style = TextStyle(letterSpacing = 7.sp),fontWeight = FontWeight(600), fontSize = 18.sp,)
                        Text(

                                if (manageCardViewModel.cardDataMask.value) cardFormat(
                                    cardno
                                ) else cardSpaceFormat(cardno)
//                            "XXXX_XXXX_XXXX_123"
                            ,
                            color = Resetcolor,
                            style = TextStyle(letterSpacing = 4.sp),
                            fontWeight = FontWeight(600),
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(com.example.prepaidcardsdk.R.font.poppins_regular))
                        )


                    }
                    Column(Modifier.padding(bottom = 10.dp)) {
                        Text(
                            "VALID\nTHRU",
                            color = Resetcolor,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(com.example.prepaidcardsdk.R.font.poppins_regular))
                        )
                        Text(
                            if (manageCardViewModel.cardDataMask.value) exp.replaceRange(
                                0,
                                exp.length,
                                "XXXX"
                            ) else expFormater(exp), color = Resetcolor
                        )

                    }

                }
            }
        }


    }
}

fun expFormater(exp: String): String {
    return exp.replaceRange(2, 3, "/" + exp[2])
}

fun cardSpaceFormat(cardno: String): String {
    return cardno.replaceRange(4, 5, " " + cardno[4]).replaceRange(9, 10, " " + cardno[9])
        .replaceRange(14, 15, " " + cardno[14])
}

fun cardFormat(cardno: String): String {
    if (cardno.isEmpty()) {
        return " "
    }
    return cardno.replaceRange(0, 12, "XXXX-XXXX-XXXX-")
}

@Composable
fun PrepaidCardBack(clickable: Modifier, manageCardViewModel: ManageCardViewModel) {

    Card(
        shape = RoundedCornerShape(5.dp),

        modifier = clickable

            .width(640.dp)
            .height(290.dp)
            .drawBehind {

            },
        colors = CardDefaults.cardColors(Color.Transparent)


    ) {

        Box(modifier = Modifier) {
            Image(
                modifier = Modifier
                    .width(680.dp)
                    .height(280.dp)

                    .padding(top = 10.dp),
                painter = painterResource(id = if(SDK_CONSTANTS.cardType.equals("GIFT",true) ) com.example.prepaidcardsdk.R.drawable.giftcard else com.example.prepaidcardsdk.R.drawable.prepaidcrop),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )

            Canvas(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(40.dp)
            ) {



            }

            Column(
                modifier = Modifier
                    .width(360.dp)
                    .height(210.dp)
                    .padding(end = 56.dp)
                    .graphicsLayer {
                        rotationY = 180f
                    },
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "For Queries call 022653666377 / 08997734322 or mail us info@card.com",
                    color = Resetcolor,

                    fontSize = 12.sp, modifier = Modifier.padding(end = 40.dp)

                )
                Text(
                    "CVV"+manageCardViewModel.cvvValue.value ?: "123",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .graphicsLayer {

                        },
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            }

        }

    }
}