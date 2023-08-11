package com.example.prepaidcard.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prepaidcard.R
import com.example.prepaidcard.ui.theme.HitextColor
import com.example.prepaidcard.ui.theme.lighttealGreen

@Composable
fun PageSix(rootNavController: NavHostController) {

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp))
        {
            Text(text = "Hi,", color = HitextColor)
            Text(text = "John!", color = HitextColor)
            Spacer(modifier = Modifier.width(20.dp))
            Icon(painter = painterResource(id = R.drawable.baseline_add_circle_outline_24), contentDescription = "add")
            Text(text = "Featured", color = lighttealGreen)
        }
    }
}