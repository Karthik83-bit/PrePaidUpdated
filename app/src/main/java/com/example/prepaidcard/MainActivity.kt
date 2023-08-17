package com.example.prepaidcard

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.AnimRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prepaidcard.navigation.NavigationController
//import com.example.prepaidcard.screens.PageFourteen
//import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcard.ui.theme.PrepaidCardTheme
import com.example.prepaidcardsdk.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.S)
@AndroidEntryPoint
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
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity,MainActivity::class.java))
                        }){
                            Text("PREPAID SDK")
                        }
                    }

                }
            }
        }
    }
}
