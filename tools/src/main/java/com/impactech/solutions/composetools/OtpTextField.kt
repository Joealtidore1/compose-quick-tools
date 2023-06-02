package com.impactech.solutions.composetools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    inputType: KeyboardType = KeyboardType.Number,
    value: String,
    count: Int = 6,
    font: FontFamily = FontFamily.Default,
    weight: FontWeight = FontWeight.W700,
    color: Color,
    onChange: (String)->Unit,
    onDone: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            modifier = modifier.wrapContentWidth(),
            value = value,

            onValueChange = {
                if (it.length <= count) {
                    onChange.invoke(it)
                    if (it.length == count) {
                        onDone.invoke(it)
                        focusManager.clearFocus(true)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = inputType
            ),
            decorationBox = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()
                ) {
                    repeat(count) {
                        CharView(
                            index = it, text =
                            value,
                            color = color,
                            font = font,
                            weight = weight
                        )
                        if(it < count-1) {
                            Spacer(modifier = modifier.width(5.dp))
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    color: Color,
    font: FontFamily,
    weight: FontWeight
) {
    val char = when {
        index == text.length -> "•"
        index > text.length -> "•"
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(40.dp)
            .padding(2.dp),
        text = char,
        style = TextStyle(
            fontSize = 48.sp,
            fontFamily = font,
            fontWeight = weight
        ),
        color = color,
        textAlign = TextAlign.Center
    )
}