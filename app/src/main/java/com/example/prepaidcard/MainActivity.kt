package com.example.prepaidcard

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prepaidcard.navigation.NavigationController
import com.example.prepaidcard.screens.PageFourteen
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcard.ui.theme.PrepaidCardTheme

@RequiresApi(Build.VERSION_CODES.S)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PrepaidCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val rootNavController = rememberNavController()
                    NavigationController(rootNavController, viewModel = ViewModel())
                }
            }
        }
    }
}
