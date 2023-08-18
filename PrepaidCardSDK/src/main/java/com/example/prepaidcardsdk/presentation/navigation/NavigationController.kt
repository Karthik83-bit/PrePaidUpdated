package com.example.prepaidcard.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.prepaidcard.screens.PageFifteen
import com.example.prepaidcard.screens.PageFourteen
import com.example.prepaidcard.screens.CardActivationScreen
import com.example.prepaidcard.screens.PageFourtyOne
import com.example.prepaidcard.screens.PageFourtyTwo
import com.example.prepaidcard.screens.PageTen
import com.example.prepaidcard.screens.Screen26
import com.example.prepaidcard.screens.TransactionInfo
import com.example.prepaidcard.screens.VerifyOTP
import com.example.prepaidcard.screens.ViewModel
//import com.example.prepaidcard.screens.PageFourteen
//import com.example.prepaidcard.screens.PageFourty
//import com.example.prepaidcard.screens.PageFourtyOne
//import com.example.prepaidcard.screens.PageFourtyTwo
//import com.example.prepaidcard.screens.PageSix
//import com.example.prepaidcard.screens.PageTen
//import com.example.prepaidcard.screens.Screen26
//import com.example.prepaidcard.screens.TransactionInfo
//import com.example.prepaidcard.screens.VerifyOTP
//import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.presentation.viewmodels.CardActivationViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.CardDataViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.GeneratePinViewModel
import com.example.prepaidcardsdk.screens.PageSix

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavigationController(rootNavController:NavHostController, viewModel: ViewModel){
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = rootNavController, startDestination = Destination.MPIN_SCREEN){

        composable(Destination.PAGE_FOURTEEN){
            PageFourteen(rootNavController,viewModel)
        }

        /*composable(Destination.SCREEN_23){
            Screen23(rootNavController )
        }*/

        composable(Destination.PAGE_SIX){
            PageSix(rootNavController, viewModel = hiltViewModel<CardDataViewModel>())
        }
        composable(Destination.CARD_MANAGEMENT_SCREEN){
            PageTen(rootNavController, viewModel= hiltViewModel<GeneratePinViewModel>() )
        }
        composable(Destination.MPIN_SCREEN){
            VerifyOTP(rootNavController = rootNavController)
        }
       /* composable(Destination.PAGE_ELEVEN){
            PageEleven(rootNavController)
        }
        composable(Destination.PAGE_EIGHTEEN){
            PageEighteen(rootNavController)
        }*/

        composable(Destination.VIEW_CARDS_1){
            CardActivationScreen(rootNavController,viewModel= hiltViewModel<CardActivationViewModel>())
//            VerifyOTP(rootNavController)
        }
        composable(Destination.TRANSACTION_STATEMENTS_HISTORY+"/{filter}", arguments = listOf(navArgument("filter"){type=
            NavType.StringType})){

            Screen26(rootNavController,it.arguments?.getString("filter"))
        }
        composable(Destination.GENERATE_PIN_SCREEN){


            val viewModel= hiltViewModel<GeneratePinViewModel>()
            PageFourtyOne(rootNavController,viewModel)
        }
        composable(Destination.ENTER_OTP_SCREEN){
            PageFourtyTwo(rootNavController)
        }
        composable(Destination.CARD_ACTIVATION_CONFIRM){
            PageFifteen(rootNavController)
        }

        composable(Destination.TRANSACTION_INFO){
            TransactionInfo(rootNavController)
        }
    }
}