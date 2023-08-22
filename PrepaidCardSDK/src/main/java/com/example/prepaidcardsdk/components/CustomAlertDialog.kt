package com.example.prepaidcardsdk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.prepaidcardsdk.R

@Composable
fun CustomAlertDialog() {
    Card(modifier = Modifier
        .size(300.dp)
        .background(White, RoundedCornerShape(5.dp))){

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Red), horizontalArrangement = Arrangement.Center){
            Icon(painter = painterResource(id = R.drawable.warning),"")
        }
    }

}