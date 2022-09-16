package com.example.calculator.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    symbol: String? = null,
    icon: Int? = null,
    iconSize: Dp = 34.dp,
    symbolColor: Color = MaterialTheme.colors.onBackground,
    background: Color = MaterialTheme.colors.secondary,
    borderColor: Color = MaterialTheme.colors.secondaryVariant,
    width: Dp = 65.dp,
    height: Dp = 65.dp,
    textStyle: TextStyle = MaterialTheme.typography.h4,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
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
                width = 1.dp,
                color = borderColor,
                shape = CircleShape
            )
    ) {
        symbol?.let {
            Text(
                text = it,
                style = textStyle,
                color = symbolColor,
                modifier = Modifier.offset(x = offsetX, y = offsetY)
            )
        }
        icon?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize)
                    .offset(x = offsetX, y = offsetY),
                colorFilter = ColorFilter.tint(symbolColor),
            )
        }
    }
    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
fun DivideButton(
    symbolColor: Color = MaterialTheme.colors.primaryVariant,
    background: Color = MaterialTheme.colors.secondary,
    borderColor: Color = MaterialTheme.colors.secondaryVariant,
    width: Dp = 65.dp,
    height: Dp = 65.dp,
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
                width = 1.dp,
                color = borderColor,
                shape = CircleShape
            )
    ) {
        Text(
            text = "\u00F7",
            color = symbolColor,
            fontSize = 42.sp,
            modifier = Modifier.offset(y = (-2).dp)
        )
    }
    Spacer(modifier = Modifier.height(18.dp))
}