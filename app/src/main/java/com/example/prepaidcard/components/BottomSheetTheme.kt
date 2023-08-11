package com.example.prepaidcard.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.prepaidcard.ui.theme.DarkColorScheme
import com.example.prepaidcard.ui.theme.LightColorScheme
import com.example.prepaidcard.ui.theme.Shapes
import com.example.prepaidcard.ui.theme.Typography


@Composable
fun BottomSheetTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}