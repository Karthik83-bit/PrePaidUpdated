package com.example.prepaidcardsdk.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcardsdk.R
import com.example.prepaidcardsdk.presentation.viewmodels.CardDataViewModel
import com.example.prepaidcardsdk.ui.theme.HitextColor
import com.example.prepaidcardsdk.ui.theme.gray_color
import com.example.prepaidcardsdk.ui.theme.isuGreen
import com.example.prepaidcardsdk.ui.theme.isuOrrange
import com.example.prepaidcardsdk.ui.theme.lighttealGreen

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PageSix(rootNavController: NavHostController, viewModel: CardDataViewModel) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(onClick = { })
            .verticalScroll(enabled = true, state = ScrollState(0))
    ) {
        if (viewModel.isError.value) {
            AlertDialog(onDismissRequest = { }) {
                Card(Modifier.size(300.dp)) {
                    Box(Modifier.fillMaxSize()) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(viewModel.errorMessage.value!!)
                            Button(onClick = {
                                viewModel.isError.value = false
                                rootNavController.popBackStack()
                            }) {
                                Text("ok")

                            }
                        }

                    }

                }

            }
        } else if (viewModel.isLoading.value) {
            AlertDialog(onDismissRequest = { }) {
                Card(Modifier.size(300.dp)) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()

                    }

                }

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row {
                Text(
                    text = "Hi,",
                    color = HitextColor,
                    fontFamily = FontFamily(Font(R.font.roboto_medium_italic)),
                    fontSize = 22.sp
                )
                Text(
                    text = " John!",
                    color = HitextColor,
                    fontFamily = FontFamily(Font(R.font.robot_medium)),
                    fontSize = 22.sp
                )
            }
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                    contentDescription = "add",
                    tint = lighttealGreen,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Featured",
                    color = lighttealGreen,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 16.sp
                )
            }
        }
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            OutlinedCard(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.outlinedCardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    Color.White
                ),
                modifier = Modifier.padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(140.dp)
                        .width(250.dp)
                ) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Prepaid Card",
                            fontFamily = FontFamily(Font(R.font.robot_medium)),
                            fontSize = 18.sp
                        )
                        Text(text = "7009 XXXX XXXX", color = gray_color)
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(80.dp)
                        ) {
                            ClickableText(
                                text = AnnotatedString("Apply Now"),
                                onClick = { rootNavController.navigate(Destination.VIEW_CARDS_1) },
                                style = TextStyle(
                                    color = isuGreen,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.roboto_regular)
                                    ),
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                            Text(
                                text = "iServeU",
                                color = isuGreen,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.inter_extra_bold)
                                ),
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }
            }
            OutlinedCard(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.outlinedCardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    Color.White
                ),
                modifier = Modifier.padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(140.dp)
                        .width(250.dp)
                ) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Gift Card",
                            fontFamily = FontFamily(Font(R.font.robot_medium)),
                            fontSize = 18.sp
                        )
                        Text(text = "7009 XXXX XXXX", color = gray_color)
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(80.dp)) {
                            ClickableText(
                                text = AnnotatedString("Apply Now"),
                                onClick = { rootNavController.navigate(Destination.PAGE_FOURTEEN) },
                                style = TextStyle(
                                    color = isuOrrange,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.roboto_regular)
                                    ),
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                            Text(
                                text = "iServeU",
                                color = isuOrrange,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.inter_extra_bold)
                                ),
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                    }
                }
            }
            OutlinedCard(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.outlinedCardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    Color.White
                ),
                modifier = Modifier.padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(140.dp)
                        .width(250.dp)
                ) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Prepaid Card",
                            fontFamily = FontFamily(Font(R.font.robot_medium)),
                            fontSize = 18.sp
                        )
                        Text(text = "7009 XXXX XXXX", color = gray_color)
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(80.dp)
                        ) {
                            ClickableText(
                                text = AnnotatedString("Apply Now"),
                                onClick = { rootNavController.navigate(Destination.VIEW_CARDS_1) },
                                style = TextStyle(
                                    color = isuGreen,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.roboto_regular)
                                    ),
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                            Text(
                                text = "iServeU",
                                color = isuGreen,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.inter_extra_bold)
                                ),
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }
            }
            OutlinedCard(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.outlinedCardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    Color.White
                ),
                modifier = Modifier.padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(140.dp)
                        .width(250.dp)
                ) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Gift Card",
                            fontFamily = FontFamily(Font(R.font.robot_medium)),
                            fontSize = 18.sp
                        )
                        Text(text = "7009 XXXX XXXX", color = gray_color)
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(80.dp)) {
                            ClickableText(
                                text = AnnotatedString("Apply Now"),
                                onClick = { rootNavController.navigate(Destination.PAGE_FOURTEEN) },
                                style = TextStyle(
                                    color = isuOrrange,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.roboto_regular)
                                    ),
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                            Text(
                                text = "iServeU",
                                color = isuOrrange,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.inter_extra_bold)
                                ),
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                    }
                }
            }
        }
        OutlinedCard(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.outlinedCardElevation(4.dp),
            colors = CardDefaults.cardColors(
                Color.White
            ),
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .width(250.dp)
            ) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Gift Card",
                        fontFamily = FontFamily(Font(R.font.robot_medium)),
                        fontSize = 18.sp
                    )
                    Text(text = "7009 XXXX XXXX", color = gray_color)
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(80.dp)) {
                        ClickableText(
                            text = AnnotatedString("Apply Now"),
                            onClick = { rootNavController.navigate(Destination.PAGE_FOURTEEN) },
                            style = TextStyle(
                                color = isuOrrange,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.roboto_regular)
                                ),
                                textDecoration = TextDecoration.Underline
                            )
                        )
                        Text(
                            text = "iServeU",
                            color = isuOrrange,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(R.font.inter_extra_bold)
                            ),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.clickable(onClick = {
            viewModel.cardData() {
                if (it != null) {
                    if (it.status == "0") {
                        rootNavController.navigate(Destination.VIEW_CARDS_1)
                    } else{
                        rootNavController.navigate(Destination.VIEW_CARDS_1)
                    }
                }
            }
        })) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "My Payments",
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.forwardspace),
                    contentDescription = "Back"
                )
            }
            Row(
                Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Recent",
                    fontFamily = FontFamily(Font(R.font.robot_medium)),
                    fontSize = 14.sp
                )
                Text(
                    text = "Received",
                    fontFamily = FontFamily(Font(R.font.robot_medium)),
                    fontSize = 14.sp,
                    color = gray_color
                )
                Text(
                    text = "Pay",
                    fontFamily = FontFamily(Font(R.font.robot_medium)),
                    fontSize = 14.sp
                )
            }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.girl_doing_online_payment),
                    contentDescription = "Girl doing online payment",
                    colorFilter = ColorFilter.tint(
                        gray_color
                    )
                )
                Text(
                    text = "Last 5 Transaction you should view",
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 14.sp,
                    color = gray_color
                )
            }
        }
    }
}