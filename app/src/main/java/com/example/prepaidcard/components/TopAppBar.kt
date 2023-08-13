package com.example.prepaidcard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.prepaidcard.R

@Composable
fun CustomTopBar(onBackPress: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(end = 10.dp),
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
                        Red, RoundedCornerShape(50)
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