package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.compose.CustomButton
import com.example.calculator.ui.compose.MainRow
import com.example.calculator.ui.compose.PrepareUI
import com.example.calculator.ui.compose.SecondRaw
import com.example.calculator.ui.theme.AppDarkestGray
import com.example.calculator.ui.theme.Blue700Border
import com.example.calculator.ui.theme.CalculaTorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculaTorTheme {
                PrepareUI {
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
                                .background(AppDarkestGray)
                                .padding(24.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                            ) {
                                CustomButton(
                                    symbol = "C",
                                    symbolColor = MaterialTheme.colors.primaryVariant
                                ) {
                                    mainText = "C"
                                }
                                CustomButton(symbol = "7") {
                                    mainText = "7"
                                }
                                CustomButton(symbol = "4") {
                                    mainText = "4"
                                }
                                CustomButton(symbol = "1") {
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
                                CustomButton(
                                    icon = R.drawable.ic_divide,
                                    iconSize = 28.dp,
                                    symbolColor = MaterialTheme.colors.primaryVariant
                                ) {
                                    mainText = "/"
                                }
                                CustomButton(symbol = "8") {
                                    mainText = "8"
                                }
                                CustomButton(symbol = "5") {
                                    mainText = "5"
                                }
                                CustomButton(symbol = "2") {
                                    mainText = "2"
                                }
                                CustomButton(symbol = "0") {
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
                                    mainText = "X"
                                }
                                CustomButton(symbol = "9") {
                                    mainText = "9"
                                }
                                CustomButton(symbol = "6") {
                                    mainText = "6"
                                }
                                CustomButton(symbol = "3") {
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
                                    symbolColor = MaterialTheme.colors.primaryVariant
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
                                    borderColor = Blue700Border,
                                    height = 142.dp
                                ) {
                                    mainText = "="
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}


