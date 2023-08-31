package com.prepaid_service_app.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.components.newBeneList
import com.example.prepaidcardsdk.data.model.resp.BeneNameResp
import com.example.prepaidcardsdk.presentation.viewmodels.BeneficiaryViewModel
import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.tealGreen

@Composable
fun beneSelectScreen(
    rootnavController: NavHostController,
    beneficiaryViewModel: BeneficiaryViewModel
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }

    var newList = remember {
        mutableStateListOf<BeneNameResp>()
    }
    LaunchedEffect(key1 = true) {
        beneficiaryViewModel.list.toList().forEach {
            newList.add(it)
        }
    }
    Scaffold(topBar = {
        CustomTopBar {
            rootnavController.popBackStack()
        }
    },
        bottomBar = {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                CustomButton(text = "Add Beneficiary", buttonColor = tealGreen) {
                    rootnavController.navigate(Destination.ADD_BENE)
                }
            }

        }
    ) {
        Column(Modifier.padding(it)) {
            Row(
                Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_person),
                    contentDescription = "Select Beneficiary",
                    tint = tealGreen
                )
                Text(
                    text = "Select Beneficiary",
                    fontFamily = FontFamily(Font(R.font.poppins_black)),
                    color = tealGreen
                )
            }
            OutlinedTextField(
                value = textState.value,
                label = {
                    Text(text = "Search")
                },
                onValueChange = {
                    textState.value = it

                    newList.removeRange(0, newList.size)
                    beneficiaryViewModel.list.forEach { bene ->
                        if (bene.title.contains(it.text, ignoreCase = true)) {
                            if (!newList.contains(bene)) {
                                newList.add(bene)
                            }
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Cultured,
                    focusedBorderColor = Cultured,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Cultured
                )
            )
            newBeneList(list = newList){
                rootnavController.navigate(Destination.SEND_MONEY_SCREEN)
            }

        }
    }
}