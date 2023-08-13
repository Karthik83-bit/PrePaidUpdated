package com.example.prepaidcard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prepaidcard.R


@Composable
fun CustomTransactionItem() {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        shape = RoundedCornerShape(2.dp)
    ) {
        Column(Modifier.padding(horizontal = 25.dp, vertical = 15.dp)){
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth()
                    ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)){
                    Icon(
                        painter = painterResource(id = R.drawable.transaction_rec), contentDescription = ""
                    )
                    Column {
                        Text("Recieved from")
                        Text("Deepak Kumar Behera")
                    }
                }
                Text("Rs 500", fontWeight = FontWeight(600))

            }
            Spacer(modifier = Modifier.height(15.dp))
            Text("24 Jul, 2022", color = Color.Gray)
        }

    }

}