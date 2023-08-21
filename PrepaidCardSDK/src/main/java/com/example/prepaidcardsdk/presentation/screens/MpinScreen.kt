package com.example.prepaidcard.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
//import com.example.prepaidcard.R
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R

@Composable
fun MpinScreen(rootNavController: NavHostController) {

    val otpValue= remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    val db = ContextCompat.getDrawable(context, R.drawable.enterotp)

    // in below line we are creating our bitmap and initializing it.
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(enabled = true, state = ScrollState(0)), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(30.dp))
        Column(verticalArrangement = Arrangement.spacedBy(50.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Box(contentAlignment = Alignment.Center){
                Image(painter = painterResource(id = R.drawable.enterotp) , contentDescription ="", modifier = Modifier
                    .size(200.dp)
                    .rotate(25f) )
                Image(painter = painterResource(id = R.drawable.key), contentDescription = "", modifier = Modifier

                    .size(100.dp)
                    .drawBehind {


                    })

            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text("Verify OTP", fontSize = (18.dp).value.sp, fontFamily = FontFamily(listOf(Font(R.font.roboto_bold))))
                Text("MPIN sent to your registered mobile number.", fontSize = 14.sp)
            }

val cont= LocalContext.current
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                BasicTextField(value = otpValue.value, onValueChange = {
                    if(it.length<=6) {
                        otpValue.value=it



                    }


                                                                       }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                )
                ){
                    Row(){
                        repeat(6){
                            val char=when{
                                it>=otpValue.value.length->"0"
                                else->otpValue.value[it]
                            }
                            Column(
                                Modifier
                                    .width(60.dp)

                                    .padding(horizontal = 10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(6.dp)) {

                                Text(char.toString(), color =if(it>=otpValue.value.length) Color.Gray else Color.Black, fontWeight = if(it>=otpValue.value.length) FontWeight(300) else FontWeight(800))


                                Row(
                                    Modifier
                                        .height(2.dp)
                                        .fillMaxWidth()
                                        .background(Color.Gray)){}


                            }
                        }
                    }


                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween){
                    Text("Generate New OTP", fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))), fontSize = 14.sp)
                    Text("Remaining time : 0:36s",fontFamily = FontFamily(listOf(Font(R.font.roboto_regular))), fontSize = 14.sp, color = Color.Gray)
                }}
            ElevatedButton(onClick = {
                rootNavController.navigate(Destination.VIEW_CARDS_SCREEN)
                                     }, shape = RoundedCornerShape(5.dp), elevation = ButtonDefaults.buttonElevation(20.dp), modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), colors = ButtonDefaults.buttonColors(Color(0xff32DBDE))) {
                Text("VERIFY OTP", fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))))
            }
        }



    }
}