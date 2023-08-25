package com.example.prepaidcardsdk.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.Cultured
import com.example.prepaidcardsdk.ui.theme.tealGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectBeneficiary(rootnavController: NavHostController) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
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
                    painter = painterResource(id = R.drawable.account_circle),
                    contentDescription = "New Beneficiary",
                    tint = tealGreen
                )
                Text(
                    text = "Add New Beneficiary",
                    fontFamily = FontFamily(Font(R.font.poppins_black)),
                    color = tealGreen
                )
            }
            OutlinedTextField(
                value = "Search",
                enabled = true,
                readOnly = false,
                onValueChange = {
                    if (it.length <= 4) {
                        it
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
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable { rootnavController.navigate(Destination.TRANSACTION) },
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedCard (modifier = Modifier.fillMaxWidth()){
                    Icon(modifier = Modifier,
                        painter = painterResource(id = R.drawable.person_pin),
                        contentDescription = "add person",
                        tint = tealGreen
                    )
                    Column {
                        Text(
                            text = "PRATIK MOHANTY",
                            fontFamily = FontFamily(Font(R.font.poppins_black))
                        )
                        Row(horizontalArrangement = Arrangement.SpaceAround) {
                            Text(
                                text = "STATE BANK OF INDIA "
                            )
                            Text(text = "xxxx xxxx xxxx 1234")
                        }
                    }
                }
            }
        }

    }

}
