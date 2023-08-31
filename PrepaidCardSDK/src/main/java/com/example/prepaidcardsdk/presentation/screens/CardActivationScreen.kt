package com.example.prepaidcard.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newui.components.CardFace
import com.example.newui.components.FlipCard
//import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomCheckField
import com.example.prepaidcard.components.CustomSheetWrap
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.components.EnterOTPPinSheet
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.components.CustomAlertDialog
import com.example.prepaidcardsdk.components.CustomLoader
import com.example.prepaidcardsdk.presentation.viewmodels.CardActivationViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.ManageCardViewModel
import com.example.prepaidcardsdk.presentation.viewmodels.VerifyOTPViewModel
import com.example.prepaidcardsdk.ui.theme.light_finocolor
import com.example.prepaidcardsdk.utils.PARAMS
import com.example.prepaidcardsdk.utils.SDK_CONSTANTS


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardActivationScreen(
    rootNavController: NavHostController,
    onClick: (state: Boolean) -> Unit = {},
    viewModel: CardActivationViewModel,
    verifyOTPViewModel: VerifyOTPViewModel,
    manageCardViewModel: ManageCardViewModel
) {
val context= LocalContext.current


Box(){
    Scaffold(topBar = {
        CustomTopBar {
            rootNavController.popBackStack()
        }
    })
    { it ->

        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        if (viewModel.isError.value) {
            AlertDialog(onDismissRequest = { }) {

                CustomAlertDialog(errMsg = viewModel.errorMessage.value) {
                    viewModel.isError.value=false
                    viewModel.errorMessage.value=""
                }
//                Card(Modifier.size(300.dp)) {
//                    Box(Modifier.fillMaxSize()) {
//
//                        Column(
//                            Modifier.fillMaxSize(),
//                            verticalArrangement = Arrangement.SpaceBetween,
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Row(){
//                                Box(Modifier.fillMaxWidth(0.8f))
//                               IconButton(onClick = {
//                                   viewModel.isError.value = false
//                                   viewModel.cardActivationToggleState.value=false
//
//                               }) {
//                                   Icon(painterResource(id = R.drawable.baseline_close_24),"")
//                               }
//                            }
//                            Icon(painterResource(id = R.drawable.baseline_error_24), contentDescription = "",Modifier.fillMaxSize(0.5f), tint = Color.Red)
//                            Box(
//                                Modifier
//                                    .weight(2f)
//                                    .padding(5.dp), contentAlignment = Alignment.Center){
//                                Text(viewModel.errorMessage.value.replaceFirstChar {
//                                                                                   it.uppercase()
//                                }, fontWeight = FontWeight(400), style = TextStyle(
//                                fontSize = 20.sp
//
//                                ),)
//
//                            }
////                            Button(onClick = {
////                                viewModel.isError.value = false
////                                viewModel.cardActivationToggleState.value=false
////
////                            }, shape = RoundedCornerShape(5.dp)) {
////                                Text("ok")
////
////                            }
//                        }
//
//                    }
//
//                }

            }
        } else if (viewModel.isLoading.value||verifyOTPViewModel.isLoading.value) {
            AlertDialog(onDismissRequest = { }) {
              CustomLoader()

            }
        }

        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .verticalScroll(enabled = true, state = ScrollState(0))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
val state= remember {
    mutableStateOf(CardFace.Front)
}

                CustomCheckField(
                    state = viewModel.cardActivationToggleState,
                    text = "Card Activation",
                    res = R.drawable.group_one
                ) {
                    viewModel.cardActivationToggleState.value =
                        !viewModel.cardActivationToggleState.value
                    verifyOTPViewModel.sendOtp(SDK_CONSTANTS.mobileNumber, params = PARAMS.activate){
                        if(it.status=="0"){
                            verifyOTPViewModel.verifyOTPScaffoldState.value=true
                        }
                        else{
                            viewModel.isError.value=true
                            viewModel.errorMessage.value=it.statusDesc
                        }
                    }








                }

                FlipCard(name = "", cardno = "****-****-****-****", exp ="***" , avlbaln ="*****" , cardfaceState = remember {
                    mutableStateOf(CardFace.Front)
                } , startanim = remember {
                    mutableStateOf(true)
                } ) {

                }
            }
        }
    }




    CustomSheetWrap(
        state = verifyOTPViewModel.verifyOTPScaffoldState,

        initOffset = 1000,
        color = if (viewModel.isError.value) Color.Red.copy(0.6f) else light_finocolor
    ) {
        EnterOTPPinSheet(state = verifyOTPViewModel.verifyOtp,manageCardViewModel,verifyOTPViewModel,
            oncancel = { verifyOTPViewModel.verifyOTPScaffoldState.value = false }) {

            manageCardViewModel.changeCardStatus(otp = verifyOTPViewModel.verifyOtp.value, status = "active"){
                if(it.status=="0"){
                    if( SDK_CONSTANTS.isVirtual!=true && SDK_CONSTANTS.isPinSet==false ){
                        rootNavController.navigate(Destination.GENERATE_PIN_SCREEN)

                    }
                    else{
                        rootNavController.navigate(Destination.CARD_MANAGEMENT_SCREEN)
                    }
                    verifyOTPViewModel.verifyOTPScaffoldState.value=false
                }
                else{
                    viewModel.isError.value=true
                    viewModel.errorMessage.value=it.statusDesc
                    verifyOTPViewModel.verifyOTPScaffoldState.value=false
                }
            }


        }
    }

}
}