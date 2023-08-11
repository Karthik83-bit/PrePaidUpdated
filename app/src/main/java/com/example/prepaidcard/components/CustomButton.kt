package com.example.prepaidcard.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prepaidcard.R


@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp,
    textColor: Color = Color.White,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = { onClick.invoke() }) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.lato_bold)),
            fontSize = textSize,
            color = textColor
        )
    }
}