//package com.example.prepaidcard.screens
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.ColumnScope
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.BottomSheetScaffold
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.rememberBottomSheetScaffoldState
//import androidx.compose.material.rememberModalBottomSheetState
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
//@Composable
//fun Screen21(clicked:()->Boolean, sheetContent:@Composable (ColumnScope.()->Unit), content:@Composable ()-> Unit,) {
//
//val bottomSheetState= rememberModalBottomSheetState(
//    skipPartiallyExpanded = true,
//
//)
//val scaffoldState= rememberBottomSheetScaffoldState(
//    bottomSheetState = bottomSheetState
//)
//val scope= rememberCoroutineScope()
//
//LaunchedEffect(key1 = clicked.invoke()){
//
//        scope.launch {
//            if (clicked.invoke()) {
//                bottomSheetState.expand()
//            } else {
//                bottomSheetState.hide()
//            }
//        }
//
//}
//
//
//
//
//BottomSheetScaffold(
//    sheetContent = sheetContent,
//    scaffoldState = scaffoldState,
//    sheetSwipeEnabled = false
//
//
//) {
//    Box(
//        Modifier
//            .fillMaxSize()
//            .background(Color.Black), contentAlignment = Alignment.Center) {
//        content()
//
//            AnimatedVisibility(visible = clicked.invoke(),
//                enter = fadeIn(tween(100)), exit = fadeOut(tween(100))
//            ) {
//                Box(
//                    Modifier
//                        .fillMaxSize()
//                        .background(Color.Black.copy(0.5f))
//                        .pointerInput(null, { Unit })){
//
//                }
//            }
//        }
//
//}
//}