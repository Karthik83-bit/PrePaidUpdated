package com.example.prepaidcard.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomButton
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.components.DropDownMenu
import com.example.prepaidcard.ui.theme.cancelGray
import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcard.utils.Destination

class ViewModel : ViewModel() {
    var selectedAction: MutableState<String> = mutableStateOf("Gift Card"); private set
}

@Composable
fun PageFourteen(
    rootNavController: NavHostController,
    viewModel: com.example.prepaidcard.screens.ViewModel
) {

    val expanded = remember {
        mutableStateOf(false)
    }

    val listOfAction = listOf("Gift Card", "GPR Card")

    val selectedAction = viewModel.selectedAction
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
    ) {
        CustomTopBar {
            (context as Activity).finish()
        }
        DropDownMenu(Modifier, expanded, listOfAction, selectedAction)
        Image(painter = painterResource(id = R.drawable.card), contentDescription = "Gift Card")
        Row {

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)) {
            CustomButton(
                text = "PROCEED",
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f),
                buttonColor = lighttealGreen
            ) {
                rootNavController.navigate(Destination.SCREEN_23)
            }
            Spacer(modifier = Modifier.width(11.dp))
            CustomButton(
                text = "CANCEL",
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f),
                buttonColor = cancelGray
            ) {
                (context as Activity).finish()
            }
        }
    }
}