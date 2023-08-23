package com.example.prepaidcardsdk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.prepaidcardsdk.R

@Composable
fun CustomAlertDialog(errMsg:String,onClick:()->Unit) {
    Card(modifier = Modifier
        .size(200.dp)
        .background(White, RoundedCornerShape(5.dp)), colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(5.dp)){
Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.3f)
        .background(Red, RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, 0.dp, 0.dp)), horizontalArrangement = Arrangement.Center){
        Icon(painter = painterResource(id = R.drawable.round_warning_24),"", tint = Yellow, modifier = Modifier.size(60.dp))}


    Text(errMsg.replaceFirstChar { it.uppercase() }, fontWeight = FontWeight(600), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

    Button(onClick = { onClick()}, colors = ButtonDefaults.buttonColors(containerColor = Red), modifier = Modifier.defaultMinSize(minWidth = 100.dp, minHeight = 40.dp,), shape = RoundedCornerShape(4.dp)) {
        Text("OK", color = White, fontWeight = FontWeight(500))
    }

}


    }

}
@Composable
fun CustomSucessDialog(msg:String,onClick:()->Unit) {
    val lottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.tr)

    )
    val startAnim= remember{
        mutableStateOf(false)
    }

    val progress by animateLottieCompositionAsState(composition = lottie)
    Card(modifier = Modifier
        .size(200.dp)
        .background(White, RoundedCornerShape(5.dp)), colors = CardDefaults.cardColors(containerColor = White), shape = RoundedCornerShape(5.dp), elevation = CardDefaults.cardElevation(10.dp)) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .background(
                        Color.Transparent,
                        RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, 0.dp, 0.dp)
                    ), horizontalArrangement = Arrangement.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.check), contentDescription = "")

            }
                Text(
                    msg.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight(500),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_regular)
                    )
                )


            }


        }

    }
