package com.example.prepaidcard.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
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
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true,){
        scope.launch {
            delay(2000)
            rootNavController.navigate(Destination.CARD_MANAGEMENT_SCREEN)
        }
    }
    Scaffold(topBar = {
        CustomTopBar {
            rootNavController.popBackStack()
        }
    }) {
        Box(modifier = Modifier.padding(it), contentAlignment = Alignment.CenterStart) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(enabled = true, state = ScrollState(0))
            )
            {
//                CustomTopBar {rootNavController.navigate(Destination.PAGE_FOURTY_TWO)}
                Column(
                    Modifier.fillMaxWidth()
                        .padding(top = 150.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.loadingone),
                        contentDescription = "loading"
                    )

                    Text(
                        text = "Card activation is in process !!",
                        color = tealGreen,
                        fontSize = 18.sp,
                        fontFamily = latoBold
                    )

                    Text(
                        text = "A URL has been triggered on your Registered mobile number please click the url to complete the KYC Process.",
                        color = CustomBlack,
                        fontSize = 14.sp,
                        fontFamily = latoBold
                    )

                }
            }
        }
    }

}