package com.example.prepaidcard.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prepaidcard.R
import com.example.prepaidcard.ui.theme.CustomBlack
import com.example.prepaidcard.ui.theme.tealGreen

@Composable
fun PageFifteen() {
    val latoBold = FontFamily(
        Font(R.font.lato_bold, FontWeight.Bold)
    )
    Box() {
        Column(modifier = Modifier
            .padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.backspace),
                    contentDescription = "backspace"
                )
                Spacer(modifier = Modifier.width(250.dp))
                Image(
                    painter = painterResource(id = R.drawable.qr_code),
                    contentDescription = "qr_code"
                )
                Image(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "notification"
                )
            }
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