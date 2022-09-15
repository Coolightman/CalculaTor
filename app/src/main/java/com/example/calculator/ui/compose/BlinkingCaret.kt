package com.example.calculator.ui.compose

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
            animation = tween(
                durationMillis = 300,
                delayMillis = 800,
                easing = LinearOutSlowInEasing
            )
        )
    )
    Spacer(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
            .background(spacerColor)
    )
}