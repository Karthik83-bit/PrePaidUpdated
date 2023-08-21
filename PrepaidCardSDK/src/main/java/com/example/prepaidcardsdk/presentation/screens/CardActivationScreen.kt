package com.example.prepaidcard.screens

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newui.components.FlipCard
//import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomCheckField
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.presentation.viewmodels.CardActivationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardActivationScreen(
    rootNavController: NavHostController,
    onClick: (state: Boolean) -> Unit = {},
    viewModel: CardActivationViewModel,
) {
val context= LocalContext.current


    Scaffold(topBar = {
        CustomTopBar {
            rootNavController.popBackStack()
        }
    }) { it ->

        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        if (viewModel.isError.value) {
            AlertDialog(onDismissRequest = { }) {
                Card(Modifier.size(300.dp)) {
                    Box(Modifier.fillMaxSize()) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(viewModel.errorMessage.value)
                            Button(onClick = {
                                viewModel.isError.value = false
                                rootNavController.navigate(Destination.GENERATE_PIN_SCREEN)
                            }) {
                                Text("ok")

                            }
                        }

                    }

                }

            }
        } else if (viewModel.isLoading.value) {
            AlertDialog(onDismissRequest = { }) {
                Card(Modifier.size(300.dp)) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()

                    }

                }

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

                FlipCard()
                CustomCheckField(
                    state = viewModel.cardActivationToggleState,
                    text = "Card Activation",
                    res = R.drawable.group_one
                ) {
                    viewModel.cardActivationToggleState.value =
                        !viewModel.cardActivationToggleState.value
                    viewModel.changeCardStatus() {response->
                        if (response != null) {
                            viewModel.response.value = response
                            Toast.makeText(context, response.statusDesc,Toast.LENGTH_LONG).show()
                            if (response.status == "0") {

                                rootNavController.navigate(Destination.GENERATE_PIN_SCREEN)

                            } else {
                                viewModel.isError.value = true
                                viewModel.errorMessage.value = response.statusDesc

                            }
                        }
                    }
                }


            }
        }
    }
}