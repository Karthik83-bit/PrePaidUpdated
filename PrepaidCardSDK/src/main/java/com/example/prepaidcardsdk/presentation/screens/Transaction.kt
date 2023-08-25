package com.example.prepaidcardsdk.presentation.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.prepaidcardsdk.R

@Composable
fun Transaction(rootNavController: NavHostController){
Row {
    Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24), contentDescription = "cancel")

    Icon(painter = painterResource(id = R.drawable.person_pin), contentDescription = "person")

    Text(text = "PRATIK MOHANTY")
    
    Text(text = "STATE BANK OF INDIA")
    
    Text(text = "xxxx xxxx 7106")
    
}
}