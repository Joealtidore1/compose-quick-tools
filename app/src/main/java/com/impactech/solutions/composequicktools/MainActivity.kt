package com.impactech.solutions.composequicktools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.impactech.solutions.composequicktools.ui.theme.ComposeQuickToolsTheme
import com.impactech.solutions.composetools.CustomButton
import com.impactech.solutions.composetools.CustomSnackBar
import com.impactech.solutions.composetools.InputField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val snackbarColor = mutableStateOf(Color.Red)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuickToolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var value by remember {
                        mutableStateOf("")
                    }
                    val snackbarState = remember {
                        SnackbarHostState()
                    }


                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarState){
                                CustomSnackBar(text = it.message, color = snackbarColor.value)
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(it)
                                .padding(15.dp)
                        ) {
                            InputField(
                                value = value,
                                onChange = {
                                    value = it
                                },
                                backgroundColor = Color.White,
                                borderColor = Color.Black,
                                placeHolder = "Enter sample text",
                                label = "Sample text",
                                onImePerformed = {}
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            CustomButton(text = "Try it", background = Color.Blue) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    //Change color before showing snackbar
                                    snackbarColor.value = Color.Blue

                                    snackbarState.showSnackbar(value.takeUnless {
                                        it.isEmpty()
                                    }?: "Text field is empty")
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuickToolsTheme {
        Greeting("Android")
    }
}