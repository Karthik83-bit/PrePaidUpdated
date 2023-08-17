package com.example.prepaidcard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconCustomButton(text:String, modifier: Modifier, color: Color, res:Int, onClick:()-> Unit) {
    Button(onClick = {onClick()},
        modifier = modifier

            .width(120.dp)


            .height(50.dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults
            .buttonColors(
                color
            )) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
            Icon( painter = painterResource(id = res),"", modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text,)
        }




    }
}