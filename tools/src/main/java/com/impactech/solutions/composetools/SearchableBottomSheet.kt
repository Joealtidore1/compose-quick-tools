package com.impactech.solutions.composetools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T>SearchableDropDown(
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
    borderColor: Color = Color.Gray,
    items: List<T>,
    hint: String,
    error: String? = null,
    value: T?,

    onClick: (()-> Unit)? = null,
    itemAsString: ((T) -> String)? = null,
    onSelect: (T) -> Unit,

) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isVisible by remember {
        mutableStateOf(false)
    }

    InputField(
        value = if(value == null) "" else if(value is String) value else itemAsString!!.invoke(value),
        onChange = {},
        placeHolder = hint,
        error = error,
        label = hint,
        readOnly = true,
        borderColor = borderColor,
        onClick = {
            onClick?.invoke()
            isVisible = true
        }
    )

    if(isVisible ){
        ModalBottomSheet(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f),
            sheetState = SheetState(skipPartiallyExpanded = true),
            onDismissRequest = { isVisible = false }
        ) {
            var searchValue by remember {
                mutableStateOf("")
            }
            var searchedItems by remember {
                mutableStateOf(items)
            }

            Column(
                modifier.padding(horizontal = 12.dp)
            ) {
                InputField(
                    value = searchValue,
                    onChange = {
                        searchValue = it
                        if (itemAsString != null) {
                            searchedItems = items.filter {item ->
                                if(item is String){
                                    item.lowercase().contains(it.lowercase())
                                }else{
                                    itemAsString(item).lowercase().contains(it.lowercase())
                                }
                            }
                        }
                    },
                    placeHolder = "Search...",
                    label = "Search",
                    borderColor = borderColor,
                    onClick = {
                        isVisible = true
                    }
                )

                Gap(size = 12)

                if(searchedItems.isEmpty()){
                    Box(modifier = modifier.fillMaxSize()){
                        NoData(
                            modifier = modifier.align(Alignment.Center)
                        )
                    }
                }

                LazyColumn{
                    items(searchedItems.size){
                        val item = searchedItems.elementAt(it)
                        Box(modifier = modifier
                            .fillMaxWidth()
                            .clickable(
                                indication = null,
                                interactionSource = NoRipple
                            ) {
                                onSelect.invoke(item)
                                isVisible = false
                                keyboardController?.hide()
                            }
                            .padding(bottom = 20.dp)){
                            if(item is String){
                                Text(text = item, style = style)
                            }else {
                                if(itemAsString != null){
                                    Text(text = itemAsString(item), style = style)
                                }
                            }
                        }

                        Divider()

                        Gap(size = 20)
                    }
                }
            }

        }
    }

}