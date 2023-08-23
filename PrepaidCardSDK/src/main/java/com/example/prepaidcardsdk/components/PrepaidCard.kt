package com.example.newui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.ui.theme.HitextColor
import com.example.prepaidcardsdk.ui.theme.Resetcolor
import com.example.prepaidcardsdk.ui.theme.tealGreen


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
fun FlipCard(name: String, cardno: String, exp: String,viewBalance: () -> Unit) {

    val cardfaceState= remember {
        mutableStateOf(CardFace.Front)
    }

    val rotatiom= animateFloatAsState(
            targetValue = cardfaceState.value.angle,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseInOut,
        )
    )
    val cont=LocalContext.current
    
    Card(

    ){

            if(cardfaceState.value.angle<90f){
AnimatedVisibility(visible = cardfaceState.value.angle<90f) {
    PrepaidCard(Modifier
        .graphicsLayer {
            rotationY = rotatiom.value

        }
        .clickable {
            cardfaceState.value = cardfaceState.value.next


        },cardno,name,exp){
viewBalance()

    }
}

            }else{
                PrepaidCardBack(Modifier
                    .graphicsLayer {
                        rotationY = rotatiom.value
                    }
                    .clickable {
                        cardfaceState.value = cardfaceState.value.next


                    }, manageCardViewModel = hiltViewModel<ManageCardViewModel>
                    ())
            }


    }


}


@Composable
fun PrepaidCard(clickable: Modifier, cardno: String, name: String, exp: String,viewBalance:()->Unit) {
    val mask= remember {
        mutableStateOf(10.dp)
    }

    var buttonText by remember {
        mutableStateOf("View Details")
    }

    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(Resetcolor),
        modifier = clickable


            .width(380.dp)
            .height(250.dp)
            .drawBehind {

            },



        ) {
        Box(){

            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)) {

                drawCircle(
                    brush = Brush.horizontalGradient(listOf(  Resetcolor, tealGreen), startX = 0f, endX = 700f),

                    radius = 900f,
                    center = Offset(x = 500f, y = 1150f)
                )
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
//           Icon(painter = painterResource(id = R.drawable.ic_launcher_background),"", tint = Color.White )

                Column() {
                    Text("NSDL", color = Color.White)
                    Text("PaymentsBank", color = Color.White, fontWeight = FontWeight(700))
                }

                Text(text = "Valid in India Only", color = Color.White,)

            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                Spacer(modifier = Modifier.height(20.dp))
                Column() {
                    Text(name, color = Color.White,style = TextStyle(letterSpacing = 5.sp, fontWeight = FontWeight(500)), modifier = Modifier.blur(mask.value))
                    Text("${cardFormat(cardno)}", color = Color.White, style = TextStyle(letterSpacing = 5.sp),fontWeight = FontWeight(500), modifier = Modifier.blur(mask.value))

                }
                OutlinedButton(
                    onClick = {
                        if (buttonText == "View Details") {
                            buttonText = "Available Balance\n ₹ 2,35,000.00"
                        }else{
                            buttonText = "View Details"
                        }
                        mask.value=if(mask.value==0.dp){
                            10.dp
                        }else{
                            0.dp
                        }
                    },
                    border = BorderStroke(1.dp, Color.White),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = tealGreen
                    )
                ) {
                    Text(buttonText, color = Color.White)

                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "12/16", color = Color.White)
                    Text("ValidThru", color = Color.White)
                    Text(text = "${exp}", color = Color.White)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_call_answer,),
                        contentDescription = "",
                        tint = Color.White,
                    )
                }
            }

        }

      }
}

fun cardFormat(cardno: String): String {
return cardno.replaceRange(4,5," "+cardno[4].toString()).replaceRange(9,10," "+cardno[9].toString()).replaceRange(14,15," "+cardno[14].toString())
}

@Composable
fun PrepaidCardBack(clickable: Modifier, manageCardViewModel: ManageCardViewModel) {

    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(tealGreen),
        modifier = clickable

            .width(380.dp)
            .height(250.dp)
            .drawBehind {

            },



        ) {
        Box {
            Canvas(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()) {

                drawRect(
                    color = HitextColor,
                    topLeft = Offset(x =0.dp.toPx(), y = 20.dp.toPx())
                )
                drawRect(
                    color = Color.White,
                    topLeft = Offset(x =200.dp.toPx(), y = 80.dp.toPx())

                )
            }
            Column (modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)){
                Text(
                    text = "For Queries call 022653666377 / 08997734322 or mail us info@card.com",
                    color = Color.White,
                    modifier = Modifier.graphicsLayer {
                        translationX = 10.dp.toPx()
                    rotationX =180f
                        rotationZ =180f
                    },
                    fontSize = 12.sp
                )
                    Text(
                        "123",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .graphicsLayer {
                                translationX = 200.dp.toPx()

                                rotationX = 180f
                                rotationZ = 180f
                            },
                        color = Color.Black
                    )

                Column {
                    Text(text = "AUTHORIZED SIGNATURE - NOT VALID UNLESS SIGNED. NOT TRANSABLE", modifier = Modifier
                        .graphicsLayer {
                            translationX = 10.dp.toPx()

                            rotationX = 180f
                            rotationZ = 180f
                        },
                        color = Color.White)
                    Text(text = "the point of using Lorem Ipsum",
                        modifier = Modifier
                            .graphicsLayer {
                                translationX = 10.dp.toPx()

                                rotationX = 180f
                                rotationZ = 180f
                            },
                        color = Color.White)
                    Text(text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                        modifier = Modifier
                            .graphicsLayer {
                                translationX = 10.dp.toPx()

                                rotationX = 180f
                                rotationZ = 180f
                            },
                        color = Color.White)
                }
                }




        }

    }
}