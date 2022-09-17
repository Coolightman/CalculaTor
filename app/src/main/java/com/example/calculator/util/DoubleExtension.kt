package com.example.calculator.util

fun Double.toIntOrNull(): Int? {
    return if (this % 1.0 == 0.0) {
        this.toInt()
    } else null
}