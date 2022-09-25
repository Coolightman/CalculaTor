package com.example.calculator.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.R
import com.example.calculator.ui.theme.Blue700Border

private const val DEF_BUTTON_SIZE = 64
private const val KEYBOARD_ROWS_NUMBER = 5
private const val KEYBOARD_SPACE_NUMBER = KEYBOARD_ROWS_NUMBER + 1

@Composable
fun CustomButton(
    symbol: String? = null,
    icon: Int? = null,
    iconSize: Dp = 34.dp,
    symbolColor: Color = MaterialTheme.colors.onBackground,
    background: Color = MaterialTheme.colors.secondary,
    borderColor: Color = MaterialTheme.colors.secondaryVariant,
    width: Dp = DEF_BUTTON_SIZE.dp,
    height: Dp = DEF_BUTTON_SIZE.dp,
    textStyle: TextStyle = MaterialTheme.typography.h4,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = width, height = height)
            .shadow(4.dp, CircleShape)
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
}

@Composable
fun DivideButton(onClick: () -> Unit) {
    CustomButton(
        symbol = "\u00F7",
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetY = (-2).dp,
        textStyle = MaterialTheme.typography.h4.copy(
            fontSize = 42.sp
        )
    ) {
        onClick()
    }
}

@Composable
fun ButtonClear(onClick: () -> Unit) {
    CustomButton(
        symbol = "C",
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetX = (-1).dp,
        offsetY = (-2).dp
    ) {
        onClick()
    }
}

@Composable
fun ButtonMultiply(onClick: () -> Unit) {
    CustomButton(
        icon = R.drawable.ic_multiply,
        symbolColor = MaterialTheme.colors.primaryVariant,
    ) {
        onClick()
    }
}

@Composable
fun ButtonMinus(onClick: () -> Unit) {
    CustomButton(
        icon = R.drawable.ic_minus,
        symbolColor = MaterialTheme.colors.primaryVariant
    ) {
        onClick()
    }
}

@Composable
fun ButtonPlus(onClick: () -> Unit) {
    CustomButton(
        icon = R.drawable.ic_plus,
        symbolColor = MaterialTheme.colors.primaryVariant
    ) {
        onClick()
    }
}

@Composable
fun ButtonEqual(
    keyboardHeightPx: Int,
    onClick: () -> Unit
) {
    val keyboardHeightDp = LocalDensity.current.run { keyboardHeightPx.toDp() }
    val space =
        (keyboardHeightDp.value - (DEF_BUTTON_SIZE * KEYBOARD_ROWS_NUMBER)) / KEYBOARD_SPACE_NUMBER
    val buttonHeight = DEF_BUTTON_SIZE * 2 + space
    CustomButton(
        icon = R.drawable.ic_equal,
        background = MaterialTheme.colors.primaryVariant,
        symbolColor = Color.White,
        borderColor = Blue700Border,
        height = buttonHeight.dp
    ) {
        onClick()
    }
}

@Composable
fun ButtonBackspace(onClick: () -> Unit) {
    CustomButton(
        icon = R.drawable.ic_backspace,
        iconSize = 28.dp,
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetX = (-2).dp
    ) {
        onClick()
    }
}

@Composable
fun ButtonNumber(number: String, onClick: () -> Unit) {
    CustomButton(
        symbol = number,
        offsetY = (-2).dp
    ) {
        onClick()
    }
}

@Composable
fun ButtonExpandBottomSheet(onClick: () -> Unit) {
    CustomButton(
        icon = R.drawable.ic_expand,
        symbolColor = MaterialTheme.colors.primaryVariant,
    ) {
        onClick()
    }
}

@Composable
fun ButtonFactorial(onClick: () -> Unit) {
    CustomButton(
        symbol = "x!",
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetY = (-2).dp,
        offsetX = (1).dp,
        textStyle = MaterialTheme.typography.h4.copy(
            fontSize = 28.sp
        )
    ) {
        onClick()
    }
}

@Composable
fun ButtonRoundToInt(onClick: () -> Unit) {
    CustomButton(
        symbol = "[X]",
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetY = (-1).dp,
        textStyle = MaterialTheme.typography.h4.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    ) {
        onClick()
    }
}

@Composable
fun ButtonExponent(onClick: () -> Unit) {
    CustomButton(
        icon = R.drawable.ic_exponent,
        iconSize = 28.dp,
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetX = (3).dp
    ) {
        onClick()
    }
}

@Composable
fun ButtonSqrt(onClick: () -> Unit) {
    CustomButton(
        icon = R.drawable.sqrt,
        iconSize = 28.dp,
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetY = 2.dp
    ) {
        onClick()
    }
}

@Composable
fun ButtonLongTitle(symbol: String, onClick: () -> Unit) {
    CustomButton(
        symbol = symbol,
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetY = (-2).dp,
        textStyle = MaterialTheme.typography.h4.copy(
            fontSize = 24.sp
        )
    ) {
        onClick()
    }
}

@Composable
fun ButtonLg(symbol: String, onClick: () -> Unit) {
    CustomButton(
        symbol = symbol,
        symbolColor = MaterialTheme.colors.primaryVariant,
        offsetY = (-2).dp,
        textStyle = MaterialTheme.typography.h4.copy(
            fontSize = 20.sp
        )
    ) {
        onClick()
    }
}