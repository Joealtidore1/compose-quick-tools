package com.impactech.solutions.composetools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/*suspend fun SnackbarHostState.show(msg: String, isError: Boolean = true): SnackbarResult {
    SnackbarColor.value = if(isError){ Color.Red }else{ Primary }
    return showSnackbar(msg)
}

suspend fun SnackbarHostState.error(msg: String): SnackbarResult{
    return  show(msg)
}

suspend fun SnackbarHostState.success(msg: String): SnackbarResult{
    return  show(msg, false)
}*/

@Composable
fun Gap(size: Number) {
    Spacer(modifier = Modifier.height(size.toFloat().dp))
}

@Composable
fun GapH(size: Number) {
    Spacer(modifier = Modifier.width(size.toFloat().dp))
}

@Composable
fun RowScope.Gap() {
    Spacer(modifier = Modifier.weight(1f, true))
}

@Composable
fun ColumnScope.Gap() {
    Spacer(modifier = Modifier.weight(1f, true))
}

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    background: Color = White,
    onBackground: Color,
    action: ()-> Unit
){
    FloatingActionButton(
        onClick = { action.invoke() },
        backgroundColor = background,
        contentColor = onBackground,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 1.dp),
        modifier = modifier
            .offset(y = 10.dp)
            .background(background, RoundedCornerShape(50))
            .padding(3.dp)
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "add user",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePicker(
    state: MaterialDialogState,
    date: LocalDate = LocalDate.now(),
    color: Color,
    style: TextStyle,
    callback: (String)-> Unit) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    MaterialDialog(
        dialogState = state,
        backgroundColor = White,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = style
            ){
                val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                callback.invoke(format.format(selectedDate))
            }
            negativeButton("Cancel", textStyle = style)
        },
    ) {
        datepicker(
            initialDate = date,
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = color,
                headerTextColor = White,
                dateActiveBackgroundColor = color,
                dateActiveTextColor = White,
                dateInactiveBackgroundColor = Color.Transparent,
                dateInactiveTextColor = Color.Black,
                calendarHeaderTextColor = Color.Black
            ),
            allowedDateValidator = {
                it == date || it.isBefore(date)
            }
        ){
            selectedDate = it
        }
    }
}