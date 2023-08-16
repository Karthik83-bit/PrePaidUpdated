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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import com.example.prepaidcard.ui.theme.ColorReset
import com.example.prepaidcard.ui.theme.tealGreen


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
fun FlipCard(){

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


        })
}

            }else{
                PrepaidCardBack(Modifier
                    .graphicsLayer {
                        rotationY = rotatiom.value
                    }
                    .clickable {
                        cardfaceState.value = cardfaceState.value.next


                    })
            }


    }


}


@Composable
fun PrepaidCard(clickable: Modifier) {
    val mask= remember {
        mutableStateOf(10.dp)
    }
    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(ColorReset),
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
                    brush = Brush.horizontalGradient(listOf(  ColorReset, tealGreen), startX = 0f, endX = 700f),

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
                    Text("DEEPAK Kumar Behera", color = Color.White,style = TextStyle(letterSpacing = 5.sp, fontWeight = FontWeight(500)), modifier = Modifier.blur(mask.value))
                    Text("0123 4567 8910 1234", color = Color.White, style = TextStyle(letterSpacing = 5.sp),fontWeight = FontWeight(500), modifier = Modifier.blur(mask.value))

                }
                OutlinedButton(
                    onClick = {
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
                    Text("ViewDetails", color = Color.White)

                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "12/16", color = Color.White)
                    Text("ValidThru", color = Color.White)
                    Text(text = "12/16", color = Color.White)
                    Icon(
                        painter = painterResource(id = com.example.prepaidcard.R.drawable.image_one,),
                        contentDescription = "",
                        tint = Color.White,
                    )
                }
            }

        }

      }
}

@Composable
fun PrepaidCardBack(clickable: Modifier) {

    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(ColorReset),
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
                    brush = Brush.horizontalGradient(listOf(  ColorReset, tealGreen), startX = 0f, endX = 700f),

                    radius = 900f,
                    center = Offset(x = 500f, y = 1150f)
                )
            }



        }

    }
}