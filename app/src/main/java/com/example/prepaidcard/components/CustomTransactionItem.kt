package com.example.prepaidcard.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prepaidcard.R


@Composable
fun CustomTransactionItem(onClick: () -> Unit, icon: Int,color:Color) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp).clickable {
                onClick()
            },
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
                        painter = painterResource(id = icon), contentDescription = "", tint = color
                    )
                    Column {
                        Text("Recieved from", fontSize = 16.sp, color = Color.Gray, fontFamily = FontFamily(
                            listOf( Font(R.font.lato_regular))))
                        Text("Deepak Kumar Behera", fontSize = 14.sp, fontFamily = FontFamily(
                            listOf( Font(R.font.lato_bold))))
                    }
                }
                Text("Rs 500", fontWeight = FontWeight(600), )

            }
            Spacer(modifier = Modifier.height(15.dp))
            Text("24 Jul, 2022", color = Color.Gray)
        }

    }

}