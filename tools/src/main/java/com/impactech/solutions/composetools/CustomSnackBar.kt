package com.impactech.solutions.composetools

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp


@Composable
fun CustomSnackBar(
    modifier: Modifier = Modifier,
    color: Color = Red,
    text: String?,
    onAction: (()-> Unit)? = null,
    actionTitle: String? = null
){
    if(text != null) {
        Snackbar(
            backgroundColor = color,
            modifier = modifier
                .padding(10.dp),
            action = if (onAction != null) {
                {
                    TextButton(onClick = onAction) {
                        Text(actionTitle?:"Ok")
                    }
                }
            } else null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = if (color == Red) R.drawable.ic_error else R.drawable.ic_success),
                    contentDescription = "status icon"
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
            }
        }
    }
}