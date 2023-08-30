package com.example.prepaidcardsdk.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.prepaidcard.components.CustomSheetWrap
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.presentation.viewmodels.SendMoneyViewModel
import com.example.prepaidcardsdk.ui.theme.cdback
import com.example.prepaidcardsdk.ui.theme.finocolor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SendMoneyScreen(viewModel: SendMoneyViewModel) {
    val scope= rememberCoroutineScope()
    Box {
        val width= remember{mutableStateOf(0)}
        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(20.dp)
        ) {
            val focusRequester=FocusRequester()
LaunchedEffect(key1 = true){
    focusRequester.requestFocus()
}

            Column(
                Modifier
                    .fillMaxWidth()
                    .blur(if (viewModel.convenienceSheet.value) 10.dp else 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier
                        .size(80.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(100.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("P", fontSize = 30.sp, fontWeight = FontWeight(600))
                }
                Text("Pratik Mohanty", fontWeight = FontWeight(700))
                Row {

                    Text("State bank of INDIA")
                    Text("**** **** 7106")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth(if(viewModel.ammount.value.length<=3)0.4f else if(viewModel.ammount.value.length<=6) 0.3f else if(viewModel.ammount.value.length<=8) 0.2f else if(viewModel.ammount.value.length<=10) 0.1f else 0f))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_currency_rupee_24),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(0.dp),
                        contentDescription = ""
                    )

                    BasicTextField(
                        singleLine = true,
                        value = viewModel.ammount.value,
                        onValueChange = {
                            viewModel.ammountSelected.value=""
                            viewModel.ammount.value = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                        modifier = Modifier
                            .wrapContentWidth()
                            .onGloballyPositioned {
                                width.value = it.size.width
                            }
                            .graphicsLayer {
                                translationX = -11f
                            }
                            .focusRequester(focusRequester),
                        textStyle = TextStyle(fontSize = 40.sp)
                    )



                    /* TextField(
                         leadingIcon = {

                         },
                         value = viewModel.ammount.value,
                         textStyle = TextStyle(fontSize = 40.sp, textAlign = TextAlign.Center),
                         onValueChange = {
                             viewModel.ammount.value = it
                         },
                         singleLine = true,
                         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                         placeholder = {
 //                            Text("0", modifier = Modifier.size(50.dp), fontSize = 40.sp)
                         },
                         colors = TextFieldDefaults.colors(
                             focusedContainerColor = Color.Transparent,
                             unfocusedIndicatorColor = Color.Transparent,
                             focusedIndicatorColor = Color.Transparent,
                             unfocusedContainerColor = Color.Transparent
                         ),
                         interactionSource = MutableInteractionSource(),
                         modifier = Modifier
                             .wrapContentSize()
                             .background(Color.Red)
                             .graphicsLayer {
                                 translationX = -60f
                             })*/
                }
                LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    items(items = viewModel.ammountOptions.toList()){
                        Button(
                            onClick = { viewModel.ammountSelected.value=it
                                      viewModel.ammount.value=it},
                            colors = ButtonDefaults.buttonColors(containerColor = if(viewModel.ammountSelected.value.equals(it)) finocolor else Color.LightGray)
                        ) {
                            Text("\u20B9" + it)
                        }
                    }


                }
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = viewModel.note.value,
                    onValueChange = {
                        viewModel.note.value = it
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(120.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.LightGray
                    ),
                    placeholder = {
                        Text("Add Note")
                    })
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text("Transfer type")
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(items = viewModel.transactionType.toList()) {
                            Button(
                                onClick = { viewModel.transactionSelected.value = it },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (viewModel.transactionSelected.value.equals(
                                            it
                                        )
                                    ) finocolor else Color.LightGray
                                )
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)){
                                    if (viewModel.transactionSelected.value.equals(it)) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.check),
                                            contentDescription = "",
                                            tint = Color.White,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Text(
                                    it,
                                    color = if (viewModel.transactionSelected.value.equals(it)) Color.White else Color.Black
                                )}
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.weight(1f))
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    OutlinedTextField(
                        value = "Fino Prepaid",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text("Balance")
                                Text("₹" + "20.00")
                            }
                        },
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.baseline_account_balance_wallet_24),
                                contentDescription = ""
                            )

                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = finocolor, disabledContainerColor = Color.LightGray),
                        onClick = {
                            viewModel.convenienceSheet.value = true
                        },
                        enabled = viewModel.ammount.value.isNotEmpty(),
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Pay", color = Color.White)


                    }

                }


            }



        }
    }
    if( viewModel.convenienceSheet.value){
        Box(Modifier.fillMaxSize().background (Color.Gray.copy(0.6f)).pointerInput(null,null,{})){

        }
    }



    CustomSheetWrap(state = viewModel.convenienceSheet, initOffset = 1000) {

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .padding(horizontal = 20.dp, vertical = 0.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Card(
                    modifier = Modifier.size(60.dp),
                    elevation = CardDefaults.cardElevation(20.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_currency_rupee_24),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(0.dp),
                        contentDescription = "", tint = finocolor
                    )}
                }
                Text("Convenience fee", fontWeight = FontWeight(800), color = finocolor, fontFamily = FontFamily(
                    Font(R.font.poppins_regular)
                ), fontSize = 20.sp)
            }
            Text("Transactions made throughy IMPS (Immediate payment Service) are chargeable", fontFamily = FontFamily(
                Font(R.font.lato_regular)
            ), modifier = Modifier.fillMaxWidth())

            OutlinedCard(Modifier.padding(horizontal = 0.dp), colors = CardDefaults.cardColors(
                cdback), shape = RoundedCornerShape(4.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(10.dp)){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Charges Applicable")
                    Text("₹6.35")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("18% GST")
                    Text("₹1.13")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total ammount Deductable")
                    Text("${viewModel.ammount.value + 6.35 + 1.13}")
                }

                }

            }
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, finocolor),
                onClick = {
                    viewModel.convenienceSheet.value = false
                },
                enabled = viewModel.ammount.value.isNotEmpty(),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Cancel")


            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = finocolor
                ),
                onClick = {
                    viewModel.convenienceSheet.value = false
                    viewModel.commingSoonSheet.value=true
                    scope.launch {
                        delay(2000)
                        viewModel.commingSoonSheet.value=false

                    }

                },
                enabled = viewModel.ammount.value.isNotEmpty(),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Pay", color = Color.White)


            }
        }
    }
    CustomSheetWrap(state = viewModel.commingSoonSheet) {
        val lottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.comminsoon)

        )
        val startAnim= remember{
            mutableStateOf(false)
        }

        val progress by animateLottieCompositionAsState(composition = lottie)
        LottieAnimation(composition =lottie , progress = { progress}, Modifier.size(200.dp))



    }
}