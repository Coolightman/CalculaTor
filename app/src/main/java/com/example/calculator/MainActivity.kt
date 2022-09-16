package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.compose.*
import com.example.calculator.ui.theme.Blue700Border
import com.example.calculator.ui.theme.CalculaTorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculaTorTheme {
                PrepareUI {
                    AppScreen()
                }
            }
        }

    }
}

@Composable
private fun AppScreen() {
    var mainText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainRow(mainText)
        SecondRaw("+100500")
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                .background(MaterialTheme.colors.surface)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                CustomButton(
                    symbol = "C",
                    symbolColor = MaterialTheme.colors.primaryVariant,
                    offsetX = (-1).dp,
                    offsetY = (-2).dp
                ) {
                    mainText = "C"
                }
                CustomButton(
                    symbol = "7",
                    offsetY = (-2).dp
                ) {
                    mainText = "7"
                }
                CustomButton(
                    symbol = "4",
                    offsetY = (-2).dp
                ) {
                    mainText = "4"
                }
                CustomButton(
                    symbol = "1",
                    offsetY = (-2).dp
                ) {
                    mainText = "1"
                }
                CustomButton(symbol = "%") {
                    mainText = "%"
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                DivideButton {
                    mainText = "\u00F7"
                }
                CustomButton(
                    symbol = "8",
                    offsetY = (-2).dp
                ) {
                    mainText = "8"
                }
                CustomButton(
                    symbol = "5",
                    offsetY = (-2).dp
                ) {
                    mainText = "5"
                }
                CustomButton(
                    symbol = "2",
                    offsetY = (-2).dp
                ) {
                    mainText = "2"
                }
                CustomButton(
                    symbol = "0",
                    offsetY = (-2).dp
                ) {
                    mainText = "0"
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                CustomButton(
                    icon = R.drawable.ic_multiply,
                    symbolColor = MaterialTheme.colors.primaryVariant
                ) {
                    mainText = "\u00D7"
                }
                CustomButton(
                    symbol = "9",
                    offsetY = (-2).dp
                ) {
                    mainText = "9"
                }
                CustomButton(
                    symbol = "6",
                    offsetY = (-2).dp
                ) {
                    mainText = "6"
                }
                CustomButton(
                    symbol = "3",
                    offsetY = (-2).dp
                ) {
                    mainText = "3"
                }
                CustomButton(symbol = ".") {
                    mainText = "."
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                CustomButton(
                    icon = R.drawable.ic_backspace,
                    iconSize = 28.dp,
                    symbolColor = MaterialTheme.colors.primaryVariant,
                    offsetX = (-2).dp
                ) {
                    mainText = "<-"
                }
                CustomButton(
                    icon = R.drawable.ic_minus,
                    symbolColor = MaterialTheme.colors.primaryVariant
                ) {
                    mainText = "-"
                }
                CustomButton(
                    icon = R.drawable.ic_plus,
                    symbolColor = MaterialTheme.colors.primaryVariant
                ) {
                    mainText = "+"
                }
                CustomButton(
                    icon = R.drawable.ic_equal,
                    background = MaterialTheme.colors.primaryVariant,
                    symbolColor = Color.White,
                    borderColor = Blue700Border,
                    height = 148.dp
                ) {
                    mainText = "="
                }
            }
        }
    }
}


@Preview(name = "Night")
@Composable
fun AppPreviewNight() {
    CalculaTorTheme(darkTheme = true) {
        PrepareUI {
            AppScreen()
        }
    }
}

@Preview(name = "Day")
@Composable
fun AppPreview() {
    CalculaTorTheme {
        PrepareUI {
            AppScreen()
        }
    }
}


