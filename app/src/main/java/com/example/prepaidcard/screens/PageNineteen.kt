package com.example.prepaidcard.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prepaidcard.R
import com.example.prepaidcard.components.BottomSheetTheme
import com.example.prepaidcard.ui.theme.ColorReset
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PageNineteen(){
            BottomSheetTheme {
                val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
                val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
                val scope = rememberCoroutineScope()

                val latoBold = FontFamily(
                    Font(R.font.lato_bold, FontWeight.Bold)
                )
                // A surface container using the 'background' color from the theme

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Reset PIN",
                                color = ColorReset,
                                fontFamily = latoBold
                            )
                        }

                    },
                    sheetShape = RoundedCornerShape(
                        topStart = 70.dp,
                        topEnd = 70.dp),
                    sheetBackgroundColor = Color.White,
                    sheetPeekHeight = 0.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        })
                        {
                            Text(text = "proceed")
                        }
                    }
                }
            }
        }