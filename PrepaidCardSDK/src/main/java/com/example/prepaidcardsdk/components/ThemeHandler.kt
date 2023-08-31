package com.example.prepaidcardsdk.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.example.prepaidcardsdk.ui.theme.PrepaidCardTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RootView(content: @Composable () -> Unit) {
    PrepaidCardTheme(darkTheme = false) {
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colorScheme.primary
        SideEffect { systemUiController.setSystemBarsColor(Color.Transparent, false) }
        Surface { content.invoke() }
    }
}