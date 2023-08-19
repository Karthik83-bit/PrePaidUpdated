package com.example.prepaidcardsdk.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.prepaidcardsdk.R

@Composable
fun CustomLoader (){

    val animation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loader))
    val progress by animateLottieCompositionAsState(composition = animation)
    Card(Modifier.size(300.dp)) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            LottieAnimation(composition = animation, progress = { progress })
        }

    }

}