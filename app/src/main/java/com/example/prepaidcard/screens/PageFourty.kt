package com.example.prepaidcard.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.newui.components.FlipCard
import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomCheckField
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.ui.theme.ColorReset
import com.example.prepaidcard.ui.theme.Cultured
import com.example.prepaidcard.ui.theme.HitextColor
import com.example.prepaidcard.utils.Destination


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
                CustomCheckField(state =cardActivationToggleState , text =  "Card Activation", res =R.drawable.group_one ) {
                    cardActivationToggleState.value=!cardActivationToggleState.value
                    rootNavController.navigate(Destination.PAGE_FOURTY_ONE)
                }




            }
        }
    }
}