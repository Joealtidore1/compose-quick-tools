package com.impactech.solutions.composetools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White.copy(alpha = 0.2f),
    borderColor: Color,
    options: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text
    ),
    trailingIcon: ImageVector? = null,
    onClick: (()->Unit)? = null,
    placeHolder: String,
    label: String,
    error: String? = null,
    isLoading: Boolean = false,
    isPassword: Boolean = false,
    readOnly: Boolean = false,
    onImePerformed: (KeyboardActionScope.() -> Unit)? = null
){
    var showPassword by remember {
        mutableStateOf(isPassword.not())
    }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            label = {
                Text(
                    label,
                    style = MaterialTheme.typography.body1,
                    color = textColor
                )
            },
            isError = error != null,
            placeholder = {
                Text(
                    placeHolder,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray.copy(alpha = 0.9f)
                )
            },
            singleLine = true,
            keyboardOptions = options,
            keyboardActions = KeyboardActions(
                onNext = onImePerformed,
                onGo = onImePerformed,
                onSearch = onImePerformed
            ),
            readOnly = readOnly,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if(isPassword){
                    IconButton(onClick = { showPassword = showPassword.not() }) {
                        Icon(
                            imageVector = if (!showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (showPassword.not()) "Hide password" else "Show password"
                        )
                    }
                }else if(trailingIcon != null){
                    if(isLoading){
                        CircularProgressIndicator(
                            color = borderColor,
                            strokeWidth = 2.5.dp,
                            modifier = modifier.size(25.dp)
                        )
                    }else{
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = "Trailing Icon"
                        )
                    }

                }
            },
            enabled = readOnly.not(),
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ){ if(!isLoading) onClick?.invoke() },
            textStyle = MaterialTheme.typography.body1.copy(color = textColor),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = textColor,
                focusedBorderColor = borderColor,
                unfocusedBorderColor = Color.Gray,
                cursorColor = borderColor,
                backgroundColor = backgroundColor,
                disabledBorderColor = borderColor.copy(alpha = 0.8f),
                disabledLabelColor = Color.Gray,
                disabledTextColor = borderColor
            )
        )
        if (error != null) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = error,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body1,
                    modifier = modifier.padding(end = 14.dp)
                )
            }
        }
    }
}