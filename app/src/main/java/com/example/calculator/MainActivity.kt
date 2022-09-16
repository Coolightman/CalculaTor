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
                ButtonClear { mainText = "C" }
                ButtonNumber(number = "7") { mainText = "7" }
                ButtonNumber(number = "4") { mainText = "4" }
                ButtonNumber(number = "1") { mainText = "1" }
                CustomButton(symbol = "%") { mainText = "%" }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                DivideButton { mainText = "\u00F7" }
                ButtonNumber(number = "8") { mainText = "8" }
                ButtonNumber(number = "5") { mainText = "5" }
                ButtonNumber(number = "2") { mainText = "2" }
                ButtonNumber(number = "0") { mainText = "0" }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                ButtonMultiply { mainText = "\u00D7" }
                ButtonNumber(number = "9") { mainText = "9" }
                ButtonNumber(number = "6") { mainText = "6" }
                ButtonNumber(number = "3") { mainText = "3" }
                CustomButton(symbol = ".") { mainText = "." }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                ButtonBackspace { mainText = "<" }
                ButtonMinus { mainText = "-" }
                ButtonPlus { mainText = "+" }
                ButtonEqual { mainText = "=" }
            }
        }
    }
}

@Preview(name = "Night")
@Composable
fun AppPreviewNight() {
    CalculaTorTheme() {
        PrepareUI {
            AppScreen()
        }
    }
}


