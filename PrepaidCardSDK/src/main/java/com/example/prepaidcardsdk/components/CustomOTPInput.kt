package com.example.prepaidcardsdk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prepaidcardsdk.ui.theme.tealGreen
import kotlinx.coroutines.delay


@Composable
fun InputView(value:TextFieldValue,onValueChange:(value:TextFieldValue)->Unit,focusRequester: FocusRequester){
    BasicTextField(value = value, onValueChange = onValueChange, modifier = Modifier
        .padding(horizontal = 10.dp)
        .clip(RoundedCornerShape(8.dp))

        .wrapContentHeight()
        .focusRequester(focusRequester),
        maxLines = 1,
        decorationBox = {
            Box (
                modifier= Modifier
                    .width(50.dp)
                    .height(70.dp),


                ){
                Column(Modifier.fillMaxSize()) {
                    it()

                    Row(modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(tealGreen)){}
                }

            }
        },
        cursorBrush = SolidColor(tealGreen),
        textStyle = TextStyle(
            color = tealGreen,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = null)
    )
}
//fun InputView(value:TextFieldValue,onValueChange:(value:TextFieldValue)->Unit,
//focusRequester: FocusRequester)
//{
//BasicTextField(value = value, onValueChange = onValueChange, modifier = Modifier
//    .padding(horizontal = 10.dp)
//    .clip(RoundedCornerShape(8.dp))
//
//    .wrapContentHeight()
//    .focusRequester(focusRequester),
//    maxLines = 1,
//    decorationBox = {
//        Card (
//            modifier= Modifier
//                .width(60.dp)
//                .height(70.dp)
//
//                ,
//            elevation = CardDefaults.cardElevation(70.dp)
//
//
//        ){
//Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
//    it()
//
//
//}
//
//
//        }
//    },
//    cursorBrush = SolidColor(tealGreen),
//textStyle = TextStyle(
//    color = tealGreen,
//    fontSize = 26.sp,
//    fontWeight = FontWeight.Bold,
//    textAlign = TextAlign.Center
//),
//    keyboardOptions = KeyboardOptions(
//        keyboardType = KeyboardType.Number,
//        imeAction = ImeAction.Done
//    ),
//    keyboardActions = KeyboardActions(onDone = null)
//)
//}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OTPInput(textList:List<MutableState<TextFieldValue>>,
requestList:List<FocusRequester>) {
val focusRequester= LocalFocusManager.current
    val keyBoardController=LocalSoftwareKeyboardController.current
    val context= LocalContext.current
    Surface(modifier = Modifier.fillMaxWidth().background(Cyan)){
Box(
    modifier=Modifier.fillMaxSize()
){
    val keyBoardcontroller=LocalSoftwareKeyboardController.current
  Row(
      Modifier
          .padding(horizontal = 16.dp)
          .padding(top = 50.dp)

          .align(Alignment.TopCenter)){
        for (i in textList.indices){
            var check=false
            InputView(value = textList[i].value,
                onValueChange = {

                    if(it.text.length==4){

                        if (keyBoardcontroller != null) {
                            keyBoardcontroller.hide()
                        }
                    }
                    if(textList[i].value.text!=""){

                        if(it.text==""){
check=true
                          textList[i].value= TextFieldValue("", selection = TextRange(0))
                           prevFocus(textList, requestList = requestList)

                        }

                        return@InputView
                    }else{

                            textList[i].value = TextFieldValue(
                                text = it.text,
                                selection = TextRange(it.text.length)
                            )
                        if(!check) {
                            nextFocus(textList, requestList)
                        }
                        }





                },
                focusRequester = requestList[i] )
        }
  }
}
    }
    LaunchedEffect(key1 = null)
    {
      delay(300)
        requestList[0].requestFocus()
    }}

fun prevFocus(textList: List<MutableState<TextFieldValue>>, requestList: List<FocusRequester>) {
    for(i in textList.indices){
        if(textList[i].value.text==""){
            if(i< textList.size && i!=0){
                requestList[i-1].requestFocus()
                break
            }
        }
    }

}

fun nextFocus(textList: List<MutableState<TextFieldValue>>,requestList: List<FocusRequester>){
    for(i in textList.indices){
        if(textList[i].value.text==""){
            if(i< textList.size){
                requestList[i].requestFocus()
                break
            }
        }
    }

}
