package com.example.prepaidcard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.ui.theme.Cultured

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    modifier: Modifier,
    expanded: MutableState<Boolean>,
    listOfAction: List<String>,
    selectedAction: MutableState<String>
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded.value) {
         Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = modifier
        .padding(24.dp)
        .clickable { expanded.value = true }) {
        OutlinedTextField(
            value = selectedAction.value, enabled = false, readOnly = true, onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() },
            placeholder = { Text(text = selectedAction.value) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Cultured,
                focusedBorderColor = Cultured,
                unfocusedBorderColor = Transparent,
                disabledBorderColor = Cultured
            ),
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expanded.value = !expanded.value })
            },
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest ={expanded.value = false},
            modifier = Modifier.width(
                with(LocalDensity.current) {
                    textFieldSize.width.toDp()
                })
            ) {
            listOfAction.forEach{ label ->
                DropdownMenuItem(onClick = {
                    selectedAction.value = label
                    expanded.value = false}, text = { Text(text = label)})


            }
            }
    }
    if (selectedAction.value == "Gift Card"){
        Image(painter = painterResource(id = R.drawable.card), contentDescription = "Gift Card")
    }
    else{
        Image(painter = painterResource(id = R.drawable.precard), contentDescription = "GPR Card")
    }
}


