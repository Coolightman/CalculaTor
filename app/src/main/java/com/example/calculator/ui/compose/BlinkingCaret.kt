package com.example.calculator.ui.compose

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BlinkingCaret(
    width: Dp = 2.dp,
    color: Color = MaterialTheme.colors.primaryVariant
) {
    val infiniteTransition = rememberInfiniteTransition()
    val spacerColor by infiniteTransition.animateColor(
        initialValue = color,
        targetValue = Color.Transparent,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                color at 0
                color at 500
                Color.Transparent at 501
                Color.Transparent at 1000
            }
        )
    )
    Spacer(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .width(width)
            .background(spacerColor)
    )
}