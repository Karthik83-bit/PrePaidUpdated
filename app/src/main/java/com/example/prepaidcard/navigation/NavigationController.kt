package com.example.prepaidcard.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prepaidcard.screens.PageEighteen
import com.example.prepaidcard.screens.PageEleven
import com.example.prepaidcard.screens.PageFourteen
import com.example.prepaidcard.screens.PageSix
import com.example.prepaidcard.screens.PageTen
import com.example.prepaidcard.screens.Screen23
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcard.utils.Destination

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavigationController(rootNavController:NavHostController, viewModel: ViewModel){
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = rootNavController, startDestination = Destination.PAGE_TEN){

        composable(Destination.PAGE_FOURTEEN){
            PageFourteen(rootNavController,viewModel)
        }

        /*composable(Destination.SCREEN_23){
            Screen23(rootNavController )
        }*/

        composable(Destination.PAGE_SIX){
            PageSix(rootNavController)
        }
        composable(Destination.PAGE_TEN){
            PageTen(rootNavController)
        }
       /* composable(Destination.PAGE_ELEVEN){
            PageEleven(rootNavController)
        }
        composable(Destination.PAGE_EIGHTEEN){
            PageEighteen(rootNavController)
        }*/
    }
}