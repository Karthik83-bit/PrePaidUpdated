package com.example.prepaidcard.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newui.components.FlipCard
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomCheckBox
import com.example.prepaidcard.ui.theme.ColorReset
import com.example.prepaidcard.ui.theme.cancelGray
import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcard.utils.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageEighteen(rootNavController: NavHostController, onClick: (state:Boolean) -> Unit = {}) {

    Scaffold(topBar = { TopAppBar(title = { Text("") }) }) {

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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val toggleState = remember {
                        mutableStateOf(false)
                    }
                    Text("CVV****", fontWeight = FontWeight(700))
                    Switch(
                        checked = toggleState.value,
                        onCheckedChange = {
                            toggleState.value = !toggleState.value
                            onClick(toggleState.value)
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = ColorReset,
                            uncheckedIconColor = ColorReset,
                            uncheckedBorderColor = ColorReset,
                            disabledUncheckedIconColor = ColorReset
                        ),
                    )

                }
                val list = listOf<String>("LoadCard", "Managecard", "Statement", "Details")
                val clickedState = remember {
                    mutableStateOf("")
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    list.forEachIndexed { index, s ->
                        Text(
                            s,
                            Modifier.clickable { clickedState.value = s },
                            color = if (s != clickedState.value) ColorReset else {
                                Color(0xFFDB8726)
                            },
                            fontWeight = FontWeight(600)
                        )
                        if (index != list.size - 1) {
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(1.dp)
                                    .background(color = ColorReset)
                            )
                        }
                    }
                }
                val checkBoxState= remember {
                    mutableStateOf("")
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.LightGray))

                if(clickedState.value=="Statement"){
                    Box(
                        Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .fillMaxSize()){
                        Column(horizontalAlignment = Alignment.Start) {
                            Text("CardStatement", fontWeight = FontWeight(600), fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                            val checkList = listOf<String>("Last 10 Transaction", "Transaction History")
                            checkList.forEach {
                                CustomCheckBox(checkBoxState, it)
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)){
                                CustomButton(text = "SUBMIT",
                                    buttonColor = lighttealGreen
                                ) {}
                                CustomButton(text = "CANCEL", buttonColor = cancelGray ) {}
                            }

                        }
                    }
                }
                else if(clickedState.value=="Managecard"){
                    rootNavController.navigate(Destination.PAGE_TEN)
                }
            }
        }
    }
}