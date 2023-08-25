package com.example.prepaidcard.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition


import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.components.IconCustomButton

import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.finocolor
import com.example.prepaidcardsdk.ui.theme.lighttealGreen

@Composable
fun TransactionInfo(rootNavController: NavHostController) {
    val lottie by rememberLottieComposition(spec =LottieCompositionSpec.RawRes(R.raw.tr)

    )
    val startAnim= remember{
        mutableStateOf(false)
    }

    val progress by animateLottieCompositionAsState(composition = lottie)

    val animatable= rememberLottieAnimatable()
    val scope= rememberCoroutineScope()
    Scaffold(topBar = { CustomTopBar {
        rootNavController.popBackStack()
    }}) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().padding(it)){
            Card(
                Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )) {
                Column(
                    Modifier
                        .fillMaxWidth()

                        .padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    LottieAnimation(composition = lottie, progress = { progress }, modifier = Modifier
                        .height(150.dp)
                        .padding(0.dp))
                    Text("Paid Sucessfully",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(){
                        Text("Paid to :", fontWeight = FontWeight(900))
                        Text("Deepak Kumar Behera",)
                    }

                }


                Column(
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                    , verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    Column(){
                        Text("Transaction Id", color = Color.Gray, fontSize = 15.sp)
                        Text("fuhugyggfr46647747y7yr498y Id", fontWeight = FontWeight(600))
                    }
                    Column() {
                        Text("Transaction Date",color= Color.Gray, fontSize = 15.sp)
                        Text("24 Jul, 2022", fontWeight = FontWeight(600))
                    }
                    Column() {
                        Text("Transaction Ammount",color= Color.Gray, fontSize = 15.sp)
                        Text("fuhugyggfr46647747y7yr498y Id", fontWeight = FontWeight(600))
                    }

                    Column() {
                        Text("Debited From",color= Color.Gray, fontSize = 15.sp)
                        Text(" SBI", fontWeight = FontWeight(600))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                        , horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        IconCustomButton(text = "Share", color = finocolor , res= R.drawable.baseline_share_24, modifier = Modifier.weight(1f)) {

                        }
                        IconCustomButton(text = "Download", color = Color.LightGray, res=R.drawable.download, modifier = Modifier.weight(1f) ) {

                        }
                    }



                }



            }
        }
    }



}