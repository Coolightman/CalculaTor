package by.coolightman.calculator.util

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

fun Int.pxToDp(density: Density): Dp = density.run { this@pxToDp.toDp() }
