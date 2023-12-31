package com.example.prepaidcardsdk.presentation.navigation

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
import com.example.prepaidcard.screens.CardACtivationConfirmationScreen
import com.example.prepaidcard.screens.ApplyCardScreen
import com.example.prepaidcard.screens.CardActivationScreen
import com.example.prepaidcard.screens.CardManagementScreen
import com.example.prepaidcardsdk.presentation.screens.EnterOTPScreen
import com.example.prepaidcard.screens.Screen26
import com.example.prepaidcard.screens.TransactionInfo
import com.example.prepaidcard.screens.MpinScreen
import com.example.prepaidcard.screens.SetPinScreen
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
import com.example.prepaidcardsdk.presentation.screens.EnterMobileNumScreen
import com.example.prepaidcardsdk.presentation.screens.SendMoneyScreen
import com.example.prepaidcardsdk.presentation.viewmodels.BeneficiaryViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.CardActivationViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.CardDataViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.GeneratePinViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.SendMoneyViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.VerifyOTPViewModel
import com.example.prepaidcardsdk.screens.ViewCardsScreen
import com.example.prepaidcardsdk.presentation.screens.AddBene
import com.example.prepaidcardsdk.presentation.screens.MyApp
import com.example.prepaidcardsdk.presentation.screens.beneSelectScreen

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavigationController(rootNavController:NavHostController, viewModel: ViewModel, verifyOTPViewModel: VerifyOTPViewModel = hiltViewModel(), beneficiaryViewModel: BeneficiaryViewModel = hiltViewModel()){
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = rootNavController, startDestination = Destination.ENTER_MOBILE_NUM_SCREEN){
composable(Destination.SEND_MONEY_SCREEN){
    val viewModel= hiltViewModel<SendMoneyViewModel>()
    SendMoneyScreen(viewModel = viewModel)
}
        composable(Destination.APPLY_CARD_SCREEN){
            ApplyCardScreen(rootNavController,viewModel)
        }

        composable(Destination.ENTER_MOBILE_NUM_SCREEN){
            EnterMobileNumScreen(rootNavController, viewModel = verifyOTPViewModel)
        }

        composable(Destination.VIEW_CARDS_SCREEN){
            val verifyOTPViewModel= hiltViewModel<VerifyOTPViewModel>()
            ViewCardsScreen(rootNavController, viewModel = hiltViewModel<CardDataViewModel>(), verifyViewModel = verifyOTPViewModel, hiltViewModel<ManageCardViewModel>())
        }
        composable(Destination.CARD_MANAGEMENT_SCREEN){
            CardManagementScreen(rootNavController,
                viewModel= hiltViewModel<GeneratePinViewModel>(),
                manageViewModel= hiltViewModel<ManageCardViewModel>() ,
                cardDataViewModel = hiltViewModel<CardDataViewModel>(),
                verifyViewModel = hiltViewModel<VerifyOTPViewModel>()
            )
        }
        composable(Destination.MPIN_SCREEN){
            MpinScreen(rootNavController = rootNavController, viewModel= verifyOTPViewModel)
        }
        composable(Destination.SELECT_BENE){
            beneSelectScreen(rootnavController = rootNavController, beneficiaryViewModel)
        }
        composable(Destination.ADD_BENE){
            AddBene(rootnavController = rootNavController, beneficiaryViewModel)
        }

        composable(Destination.CARD_ACTIVATION_SCREEN){
            CardActivationScreen(rootNavController,viewModel= hiltViewModel<CardActivationViewModel>(), verifyOTPViewModel = hiltViewModel<VerifyOTPViewModel>(), manageCardViewModel = hiltViewModel<ManageCardViewModel>())

        }
        composable(Destination.TRANSACTION_STATEMENTS_HISTORY){

            Screen26(rootNavController,it.arguments?.getString("filter"))
        }
        composable(Destination.TRANSACTION_STATEMENTS_HISTORY+"/{filter}", arguments = listOf(navArgument("filter"){type=
            NavType.StringType})){

            Screen26(rootNavController,it.arguments?.getString("filter"))
        }
        composable(Destination.GENERATE_PIN_SCREEN){


            val viewModel= hiltViewModel<GeneratePinViewModel>()
            SetPinScreen(rootNavController,viewModel, manageViewModel = hiltViewModel<ManageCardViewModel>(), verifyOTPViewModel = hiltViewModel<VerifyOTPViewModel>())
        }
        composable(Destination.ENTER_OTP_SCREEN){
            val viewModel= hiltViewModel<ManageCardViewModel>()
            EnterOTPScreen(rootNavController,viewModel, verifyOTPViewModel)
        }
        composable(Destination.CARD_ACTIVATION_CONFIRM){
            CardACtivationConfirmationScreen(rootNavController)
        }

        composable(Destination.TRANSACTION_INFO){
            TransactionInfo(rootNavController)
        }
        composable(Destination.DOWNLOAD_PDF){
            MyApp()
        }
    }
}