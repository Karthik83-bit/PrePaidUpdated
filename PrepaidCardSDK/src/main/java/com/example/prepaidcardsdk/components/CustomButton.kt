package com.example.prepaidcard.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp,
    textColor: Color = Color.White,
    buttonColor: Color,

    enable: Boolean=true,
            onClick: () -> Unit,
) {
    androidx.compose.material3.Button(
        enabled = enable,
        onClick = { onClick() },
        modifier = modifier
            .width(140.dp)
            .height(50.dp),
        shape = RoundedCornerShape(5.dp),
        colors = androidx.compose.material3.ButtonDefaults
            .buttonColors(
                buttonColor
            )
    ) {
        androidx.compose.material3.Text(text = text, fontSize = textSize, color = textColor)
    }
}
