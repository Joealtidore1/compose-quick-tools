package com.impactech.solutions.composetools

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isEnable: Boolean = true,
    text: String,
    background: Color,
    disableBackgroundColor: Color = Color.Gray,
    disableContentColor: Color = Color.Black,
    contentColor: Color = White,
    onClick: () -> Unit
){
    Button(
        onClick = if(isLoading) {
            {}
        } else onClick,
        enabled = isEnable,
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background,
            contentColor = contentColor,
            disabledBackgroundColor = disableBackgroundColor,
            disabledContentColor = disableContentColor
        )

    ) {
        if(isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                strokeWidth = 2.5.dp,
            )
        }else{
            Text(
                text = text,
                style = MaterialTheme.typography.button,
            )
        }
    }
}