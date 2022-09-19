package com.example.calculator.util

import java.math.BigDecimal
import java.text.DecimalFormat

fun String.formatResult(): String {
    var fResult = this.toPlainString()

    val decimalIndex = fResult.indexOf(DECIMAL_SEPARATOR)

    when {
//        check Integer in Double value --> ***.0
        isIntegerInDouble(fResult) -> {
            fResult = fResult.dropLast(2)
            return fResult.getFormattedResult(getPatternE(RESULT_MAX_LENGTH - 4))
        }

//        check else Integer value
        decimalIndex == -1 -> {
            return fResult.getFormattedResult(getPatternE(RESULT_MAX_LENGTH - 4))
        }

//        double less then 1
        decimalIndex == 1 && fResult[0] == '0' -> {
            val fResultDouble = fResult.toDouble()
            return if (fResultDouble < 0.0001) {
                fResult.getFormattedResult(getPatternE(RESULT_MAX_LENGTH - 4))
            } else {
                fResult.getFormattedResult(getPattern(RESULT_MAX_LENGTH - 2))
            }
        }

//        double more then 1
        decimalIndex != -1 -> {
            return fResult.getFormattedDouble()
        }
    }
    return ERROR_MESSAGE
}

private fun String.getFormattedDouble(): String {
    if (this.length > RESULT_MAX_LENGTH) {
        val beforeDecimalLength =
            this.substring(0, this.indexOf(DECIMAL_SEPARATOR)).length

        val dec = when {
            beforeDecimalLength <= 3 -> DecimalFormat(getPattern(RESULT_MAX_LENGTH - 4))
            beforeDecimalLength == 4 -> DecimalFormat(getPattern(RESULT_MAX_LENGTH - 5))
            beforeDecimalLength == 5 -> DecimalFormat(getPattern(RESULT_MAX_LENGTH - 6))
            beforeDecimalLength == 6 -> DecimalFormat(getPattern(RESULT_MAX_LENGTH - 7))
            beforeDecimalLength == 7 -> DecimalFormat(getPattern(RESULT_MAX_LENGTH - 8))
            beforeDecimalLength == 8 -> DecimalFormat(getPattern(RESULT_MAX_LENGTH - 9))
            beforeDecimalLength > 8 -> DecimalFormat(getPatternE(RESULT_MAX_LENGTH - 6))
            else -> throw NumberFormatException()
        }
        return dec.format(this.toDouble())
    }
    return this
}

private fun isIntegerInDouble(fResult: String) = fResult.takeLast(2) == ".0"

private fun String.getFormattedResult(pattern: String): String {
    return if (this.length > RESULT_MAX_LENGTH) {
        val dec = DecimalFormat(pattern)
        dec.format(this.toDouble())
    } else this
}

/**        expand any result to non E format **/
private fun String.toPlainString() = BigDecimal(this).toPlainString()

private fun getPattern(numb: Int): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("#.")
    if (numb == 1) return "#.#"
    for (i in 1..numb) {
        stringBuilder.append("#")
    }
    return stringBuilder.toString()
}

private fun getPatternE(numb: Int): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("#.")
    if (numb == 1) return "#.#E0"
    for (i in 1..numb) {
        stringBuilder.append("#")
    }
    stringBuilder.append("E0")
    return stringBuilder.toString()
}