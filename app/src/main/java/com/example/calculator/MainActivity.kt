package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.compose.MainRow
import com.example.calculator.ui.compose.PrepareUI
import com.example.calculator.ui.compose.SecondRaw
import com.example.calculator.ui.theme.CalculaTorTheme
import com.example.calculator.ui.theme.GrayLittleBrighter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculaTorTheme {
                PrepareUI {
                    var secondText by remember {
                        mutableStateOf("100500")
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        MainRow("48+151-623*42")
                        SecondRaw(secondText)
                        Spacer(modifier = Modifier.height(24.dp))

                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                                .background(MaterialTheme.colors.secondary)
                                .padding(18.dp)
                        ) {
                            CustomButton(symbol = "1") {
                                secondText = "1 is clicked"
                            }
                            CustomButton(symbol = "2") {
                                secondText = "2 is clicked"
                            }
                            CustomButton(symbol = "3") {
                                secondText = "3 is clicked"
                            }
                            CustomButton(symbol = "4") {
                                secondText = "4 is clicked"
                            }
                            CustomButton(symbol = "5") {
                                secondText = "5 is clicked"
                            }

                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun CustomButton(
        symbol: String? = null,
        icon: ImageVector? = null,
        symbolColor: Color = MaterialTheme.colors.onBackground,
        background: Color = MaterialTheme.colors.secondary,
        width: Dp = 60.dp,
        height: Dp = 60.dp,
        textStyle: TextStyle = MaterialTheme.typography.h4,
        onClick: () -> Unit
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(width = width, height = height)
                .shadow(6.dp, CircleShape)
                .background(background)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = Color.Black)
                ) { onClick() }
                .border(
                    width = 2.dp,
                    color = GrayLittleBrighter,
                    shape = CircleShape
                )
        ) {
            symbol?.let {
                Text(
                    text = it,
                    style = textStyle,
                    color = symbolColor
                )
            }
            icon?.let {
                Image(imageVector = it, contentDescription = null)
            }
        }
    }
}


