package com.example.prepaidcard.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.newui.components.FlipCard
//import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomCheckField
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageFourty(rootNavController: NavHostController, onClick: (state:Boolean)->Unit = {}){

    Scaffold(topBar = { CustomTopBar {
        rootNavController.popBackStack()
    }}) {

        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        var cardActivationToggleState = remember {
            mutableStateOf(false)
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

                FlipCard()
                CustomCheckField(state =cardActivationToggleState , text =  "Card Activation", res = R.drawable.group_one ) {
                    cardActivationToggleState.value=!cardActivationToggleState.value
                    rootNavController.navigate(Destination.GENERATE_PIN_SCREEN)
                }




            }
        }
    }
}