package com.example.prepaidcard.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prepaidcard.R
import com.example.prepaidcard.ui.theme.HitextColor
import com.example.prepaidcard.ui.theme.gray_color
import com.example.prepaidcard.ui.theme.isuGreen
import com.example.prepaidcard.ui.theme.isuOrrange
import com.example.prepaidcard.ui.theme.lighttealGreen
import com.example.prepaidcard.utils.Destination

@Composable
fun PageSix(rootNavController: NavHostController) {

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(onClick = { rootNavController.navigate(Destination.VIEW_CARDS_1) })
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        {
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
            Spacer(modifier = Modifier.width(140.dp))
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
        Row() {
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
        Row(Modifier.padding(5.dp), horizontalArrangement = Arrangement.spacedBy(180.dp)) {
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
        Row(Modifier.padding(5.dp), horizontalArrangement = Arrangement.spacedBy(85.dp)) {
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
        Image(
            painter = painterResource(id = R.drawable.girl_doing_online_payment),
            contentDescription = "Girl doing online payment",
            Modifier.padding(start = 80.dp),
            colorFilter = ColorFilter.tint(
                gray_color
            )
        )
        Text(
            text = "Last 5 Transaction you should view",
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 14.sp,
            modifier = Modifier.absolutePadding(left = 50.dp),
            color = gray_color
        )
    }
}