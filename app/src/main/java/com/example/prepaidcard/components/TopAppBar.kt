package com.example.prepaidcard.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.prepaidcard.R

@Composable
fun CustomTopBar(onBackPress: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onBackPress.invoke()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.backspace),
                contentDescription = "Back Arrow"
            )
        }
        Spacer(modifier = Modifier.width(220.dp))
            Icon(painter = painterResource(id = R.drawable.message),
                contentDescription = "message")
            Icon(painter = painterResource(id = R.drawable.qr_code),
                contentDescription = "qr_code")

            Icon(painter = painterResource(id = R.drawable.notification),
                contentDescription = "notification")

    }
}