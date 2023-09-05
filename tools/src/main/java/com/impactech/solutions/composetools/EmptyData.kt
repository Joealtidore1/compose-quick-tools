package com.impactech.solutions.composetools

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun NoData(
    modifier: Modifier = Modifier,
    text: String = "No Data",
    style: TextStyle = TextStyle()
) {
    val rotate = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = rotate){
        while (true){
            repeat(4){
                rotate.animateTo(
                    targetValue = -90f,
                    animationSpec = tween(100)
                )

                rotate.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(100)
                )
            }


            delay(5000)
        }
    }
    Box(modifier = modifier.fillMaxSize()){
        Column(
            modifier = modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = ImageVector
                    .vectorResource(
                        id = R.drawable.empty_state
                    ),
                contentDescription = null,
                modifier = modifier
                    .size(150.dp)
                    .rotate(rotate.value)
            )

            Text(
                text = text,
                style = style
            )
        }
    }
}