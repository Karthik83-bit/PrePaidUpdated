package com.example.aepssdk.presenter.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prepaidcard.R

@Composable
fun InputTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    drawable: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Done,
    hints: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxDigit: Int,
    isError: Boolean = false,
    errorMessage: String = "",
    isReadable: Boolean = false,
    isEnable: Boolean = true

) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            readOnly = isReadable,
            onValueChange = {
                if (it.length <= maxDigit) {
                    onTextChange(it)
                }
            },
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor =
                if (text.isEmpty()) Color.White else MaterialTheme.colors.background,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = if (text.isEmpty()) Color.Transparent else Color.Gray,
                disabledBorderColor = if (text.isEmpty()) Color.Transparent else Color.Gray
            ),
            trailingIcon = {
                if (isError)
                    Icon(
                        painter = painterResource(id = com.example.prepaidcard.R.drawable.baseline_error_24),
                        "error",
                        tint = MaterialTheme.colors.error
                    )
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 15.sp
            ),
            enabled = isEnable,
            label = {
                Text(
                    text = hints,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = drawable),
                    contentDescription = "icon",
                    tint = if (text.isNotEmpty()) Color.Gray else MaterialTheme.colors.onSurface.copy(
                        alpha = ContentAlpha.disabled
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

}



class MaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }
}


fun maskFilter(text: AnnotatedString): TransformedText {
    // NNNNN-NNN
    val trimmed = text.text
    var out = ""


    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 3) out += "-"
        if (i == 7) out += "-"
        if (out.length == 10) {
            out = "XXXX-XXXX" + "-"
        }
    }
    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 12) return offset + 2
            return 12
        }


        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 8) return offset - 1
            return 13
        }

    }
    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}