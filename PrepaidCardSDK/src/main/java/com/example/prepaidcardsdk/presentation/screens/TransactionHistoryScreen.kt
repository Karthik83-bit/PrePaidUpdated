package com.example.prepaidcard.screens


import android.os.Build

import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

//import com.example.prepaidcard.R
import com.example.prepaidcard.components.CustomTopBar
import com.example.prepaidcard.components.CustomTransactionItem
import com.example.prepaidcard.utils.Destination
import com.example.prepaidcard.utils.FilterOption
import com.example.prepaidcardsdk.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen26(rootNavController: NavHostController, string: String?) {
    val showFromDateState = remember {
        mutableStateOf(false)
    }
    val showToDateState = remember {
        mutableStateOf(false)
    }
    val filterState = remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true){
        if (string != null) {
            filterState.value=string
        }
    }
    val fromDateState = rememberDatePickerState()
    val todateState = rememberDatePickerState()
    val showFilterState = remember {
        mutableStateOf(false)
    }
    val filterPosition = remember {
        mutableStateOf(Offset(0f, 0f))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertMillisToDate(millis: Long, formatPattern: String = "yyyy-MM-dd HH:mm:ss"): String {
//        val instant = Instant.ofEpochMilli(millis)
//        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
//        val formatter = DateTimeFormatter.ofPattern(formatPattern)
//        return localDateTime.format(formatter)
        val dateFormat = SimpleDateFormat(formatPattern, Locale.getDefault())
        val date = Date(millis)
        return dateFormat.format(date).split(" ")[0]
    }

Scaffold(topBar = { CustomTopBar {
    rootNavController.popBackStack()
}}) {
    Column(modifier = Modifier.padding(it)) {





        LazyColumn {
            item {  Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color.White)
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Transactions", fontSize = 20.sp, fontWeight = FontWeight(700))
                Box(Modifier) {
                    IconButton(onClick = {
                        showFilterState.value = !showFilterState.value
                    }) {
                        Icon(modifier = Modifier.onGloballyPositioned {
                            filterPosition.value = it.positionInWindow()
                        },
                            painter = painterResource(id = R.drawable.baseline_filter_list_24),
                            tint = Color.Gray,
                            contentDescription = ""
                        )

                    }

                    DropdownMenu(
                        expanded = showFilterState.value,
                        modifier = Modifier.background(Color.White),
                        onDismissRequest = { showFilterState.value = !showFilterState.value },
                    ) {
                        listOf<String>(
                            FilterOption.SelectDate,
                            FilterOption.SUCCESS,
                            FilterOption.Pending,
                            FilterOption.Failed,

                        ).forEach {
                            val icon = mutableStateOf(R.drawable.baseline_calendar_month_24)
                            val color = mutableStateOf(Color.Green)
                            DropdownMenuItem(text = { Text(it) },
                                contentPadding = PaddingValues(
                                    end = 80.dp,
                                    start = 10.dp,
                                    top = 0.dp,
                                    bottom = 0.dp
                                ),
                                leadingIcon = {
                                    if (it == FilterOption.SelectDate) {
                                        icon.value = R.drawable.baseline_calendar_month_24
                                        color.value = Color.Black

                                    } else if (it == FilterOption.SUCCESS) {
                                        icon.value = R.drawable.baseline_check_circle_24
                                        color.value = Color.Green
                                    } else if (it == FilterOption.Failed) {
                                        icon.value = R.drawable.baseline_dangerous_24
                                        color.value = Color.Red

                                    } else {
                                        icon.value = R.drawable.baseline_incomplete_circle_24
                                        color.value = Color(0xffF7A442)
                                    }
                                    Icon(
                                        painter = painterResource(id = icon.value),
                                        contentDescription = "",
                                        tint = color.value
                                    )
                                },
                                onClick = {
                                    filterState.value = it
                                    showFilterState.value = !showFilterState.value
                                })
                        }

                    }


                }


            } }
            if(filterState.value==FilterOption.SelectDate){
                item {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Row(modifier = Modifier
                            .weight(1f)
                            .background(Color.LightGray, RoundedCornerShape(5.dp))
                            .height(35.dp)
                            .padding(horizontal = 10.dp)
                            .clickable {
                                showFromDateState.value = !showFromDateState.value
                            },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(fromDateState.selectedDateMillis?.let { convertMillisToDate(it) }
                                ?: "From Date", color = Color.Gray)
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                                contentDescription = "",
                                tint = Color.Gray
                            )
                        }
                        Row(modifier = Modifier
                            .weight(1f)
                            .background(Color.LightGray, RoundedCornerShape(5.dp))
                            .height(35.dp)
                            .padding(horizontal = 10.dp)
                            .clickable {
                                showToDateState.value = !showToDateState.value
                            },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(todateState.selectedDateMillis?.let { convertMillisToDate(it) }
                                ?: "To Date", color = Color.Gray)
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                                contentDescription = "",
                                tint = Color.Gray
                            )
                        }


                        AnimatedVisibility(
                            visible = showFromDateState.value,
                            enter = slideInVertically(tween(1000))
                        ) {
                            DatePickerDialog(onDismissRequest = {
                                showFromDateState.value = !showFromDateState.value
                            }, dismissButton = {
                                Button(onClick = {
                                    showFromDateState.value = !showFromDateState.value
                                }) {
                                    Text(text = "Dismiss")
                                }
                            }, confirmButton = {
                                Button(onClick = {
                                    showFromDateState.value = !showFromDateState.value
                                }) {
                                    Text(text = "Confirm")
                                }
                            }) {
                                DatePicker(state = fromDateState)
                            }
                        }
                        AnimatedVisibility(
                            visible = showToDateState.value,
                            enter = slideInVertically(tween(1000))
                        ) {
                            DatePickerDialog(onDismissRequest = {
                                showToDateState.value = !showToDateState.value
                            }, dismissButton = {
                                Button(onClick = { showToDateState.value = !showToDateState.value }) {
                                    Text(text = "Dismiss")
                                }
                            }, confirmButton = {
                                Button(onClick = { showToDateState.value = !showToDateState.value }) {
                                    Text(text = "Confirm")
                                }
                            }) {
                                DatePicker(state = todateState)
                            }
                        }
                    }


                }
            }
            if(filterState.value==FilterOption.SUCCESS){
                items(10) {
                    CustomTransactionItem({rootNavController.navigate(Destination.TRANSACTION_INFO)},icon=R.drawable.transactionpaid, color = Color(0xff32ddb5))
                }}
                else if(filterState.value==FilterOption.Failed){
                items(10) {
                    CustomTransactionItem({rootNavController.navigate(Destination.TRANSACTION_INFO)},icon=R.drawable.transaction_failed, color = Color(0xfffd6b6b))
                }

                }
            else{
                items(2) {
                    CustomTransactionItem({rootNavController.navigate(Destination.TRANSACTION_INFO)},icon=R.drawable.transactionpaid, color = Color(0xff32ddb5))
                }
                items(1) {
                    CustomTransactionItem({rootNavController.navigate(Destination.TRANSACTION_INFO)},icon=R.drawable.transaction_failed, color = Color(0xfffd6b6b))
                }
                items(3) {
                    CustomTransactionItem({rootNavController.navigate(Destination.TRANSACTION_INFO)},icon=R.drawable.transaction_rec, color = Color(0xfff04141))
                }
                items(10) {
                    CustomTransactionItem({rootNavController.navigate(Destination.TRANSACTION_INFO)},icon=R.drawable.transactionpaid, color = Color(0xff32ddb5))
                }

            }





        }

    }
}


}