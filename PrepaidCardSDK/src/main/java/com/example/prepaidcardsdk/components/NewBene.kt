package com.example.prepaidcardsdk.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.data.model.resp.BeneNameResp
import com.example.prepaidcardsdk.ui.theme.tealGreen

@Composable
fun NewBene(
    title: String,
    subtitle: String,
    accNum: String,
    onclick: () -> Unit,
    delete: () -> Unit

){

    Row(
        Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { onclick() },
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.padding(6.dp),
                painter = painterResource(id = R.drawable.person_pin),
                contentDescription = "add person",
                tint = tealGreen
            )
            Column {
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = title,
                    fontFamily = FontFamily(Font(R.font.poppins_black))
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(6.dp).fillMaxWidth()
                ) {
                    Text(
                        text = subtitle
                    )
                    Text(text = maskString(accNum))
                    Icon(painter = painterResource(id = R.drawable.removebene),
                        contentDescription = "beneRemove",
                        modifier = Modifier.clickable { delete()})
                }
            }
        }


    }
}
@Composable
fun newBeneList(list: MutableList<BeneNameResp>, onClick: () -> Unit) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        list.map { item ->
            NewBene(
                title = item.title,
                subtitle = item.subtitle,
                accNum = item.accNum,
                onclick = {
                    onClick()
                }
                ) {
                list.remove(item)
            }
        }
    }
}

fun maskString(input: String): String {
    val length = input.length
    val maskedLength = length - 4
    val maskedPart = "x".repeat(maskedLength)
    val lastFourDigits = input.substring(maskedLength)

    return "$maskedPart$lastFourDigits"
}




