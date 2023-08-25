package com.example.prepaidcard.components

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.lighttealGreen

@Composable
fun CustomTopBar(onBackPress: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(end = 10.dp).background(White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)


                .padding(0.dp), horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                onBackPress.invoke()
            }) {

                Icon(
                    painter = painterResource(id = R.drawable.backspace),
                    contentDescription = "Back Arrow",

                    )
            }
        }
        Row(

            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.message),
                contentDescription = "message",
                modifier = Modifier
                    .background(
                        Color(0xffDB8726), RoundedCornerShape(50)
                    )
                    .padding(7.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.qr_code),
                contentDescription = "qr_code",
                modifier = Modifier
                    .background(
                        Transparent, RoundedCornerShape(50)
                    )
                    .padding(7.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "notification",
                modifier = Modifier
                    .background(
                        Transparent, RoundedCornerShape(50)
                    )
                    .padding(7.dp)
            )
        }
    }
}