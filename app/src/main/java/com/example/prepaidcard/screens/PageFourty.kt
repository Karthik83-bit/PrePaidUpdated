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
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.ui.theme.ColorReset
import com.example.prepaidcard.ui.theme.Cultured
import com.example.prepaidcard.ui.theme.HitextColor
import com.example.prepaidcard.utils.Destination


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageFourty(rootNavController: NavHostController, onClick: (state:Boolean)->Unit = {}){

    Scaffold(topBar = { TopAppBar({ CustomTopBar {} }) }) {

        var textFieldSize by remember { mutableStateOf(Size.Zero) }

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

                OutlinedTextField(
                    value = "Card Activation",
                    enabled = false,
                    readOnly = true,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.group_one),
                            contentDescription = "first card"
                        )
                    },
                    placeholder = {
                        Column(modifier = Modifier.padding(5.dp)) {

                            Text(
                                text = "Card Activation", color = HitextColor,
                                fontFamily = FontFamily(
                                    Font(R.font.lato_bold)
                                )
                            )
                            Text(
                                text = "Activate your Prepaid Card",
                                color = HitextColor,
                                fontFamily = FontFamily(Font(R.font.lato_regular))
                            )
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Cultured,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Cultured
                    ),
                    trailingIcon = {
                        val toggleState = remember {
                            mutableStateOf(false)
                        }
                        Switch(
                            checked = toggleState.value,
                            onCheckedChange = {
                                toggleState.value = !toggleState.value
                                onClick(toggleState.value)
                                rootNavController.navigate(Destination.PAGE_FOURTY_ONE)
                            },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = ColorReset,
                                uncheckedIconColor = ColorReset,
                                uncheckedBorderColor = ColorReset,
                                disabledUncheckedIconColor = ColorReset
                            ),
                        )
                    }
                )
            }
        }
    }
}