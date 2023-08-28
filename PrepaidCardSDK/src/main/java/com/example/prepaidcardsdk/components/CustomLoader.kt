package com.example.prepaidcardsdk.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
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
    val rotate= remember{
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true, ){
        rotate.value=true
    }
    val rotat=animateFloatAsState(targetValue = if(rotate.value)5f else -5f, animationSpec = infiniteRepeatable(animation = tween(1000,0, easing = EaseInOut), repeatMode = RepeatMode.Reverse))


    val animation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.new_loader))
    val progress by animateLottieCompositionAsState(composition = animation, iterations = LottieConstants.IterateForever)

            LottieAnimation(composition = animation, progress = { progress }, modifier = Modifier.size(900.dp).scale(2f) )


}