package com.example.prepaidcard.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
//import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomTopBar

import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.CustomBlack
import com.example.prepaidcardsdk.ui.theme.tealGreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PageFifteen(rootNavController: NavHostController) {
    val latoBold = FontFamily(
        Font(R.font.lato_bold, FontWeight.Bold)
    )
    val scope= rememberCoroutineScope()
    LaunchedEffect(key1 = true,){
        scope.launch {
            delay(2000)
            rootNavController.navigate(Destination.CARD_MANAGEMENT_SCREEN)
        }
    }
    Scaffold(topBar = { CustomTopBar {
        rootNavController.popBackStack()
    }}) {
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier
                .padding(20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
//                CustomTopBar {rootNavController.navigate(Destination.PAGE_FOURTY_TWO)}
                Spacer(modifier = Modifier.height(150.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.loadingone),
                        contentDescription = "loading"
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Card activation is in process !!",
                        color = tealGreen,
                        fontSize = 18.sp,
                        fontFamily = latoBold
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "A URL has been triggered on your Registered mobile number please click the url to complete the KYC Process.",
                        color = CustomBlack,
                        fontSize = 14.sp,
                        fontFamily = latoBold
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

}