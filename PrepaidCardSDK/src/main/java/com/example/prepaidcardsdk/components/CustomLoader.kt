package com.example.prepaidcardsdk.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.prepaidcardsdk.R

@Composable
fun CustomLoader (){

    val animation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loader))
    val progress by animateLottieCompositionAsState(composition = animation, iterations = LottieConstants.IterateForever)
    Card(Modifier.size(200.dp), colors = CardDefaults.cardColors(Color.White)) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            LottieAnimation(composition = animation, progress = { progress }, modifier = Modifier.size(100.dp) )
        }

    }

}