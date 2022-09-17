package com.example.calculator.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.util.RESULT_MAX_LENGTH

private const val MIN_FONT_SIZE = 20
private const val DEFAULT_FONT_SIZE = 48
private const val DEFAULT_CARET_HEIGHT = 64

@Composable
fun MainRow(text: String) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        var fontSize by remember {
            mutableStateOf(DEFAULT_FONT_SIZE.sp)
        }

        var caretHeight by remember {
            mutableStateOf(DEFAULT_CARET_HEIGHT.dp)
        }

        LaunchedEffect(key1 = text.isEmpty() || text.length <= RESULT_MAX_LENGTH) {
            fontSize = DEFAULT_FONT_SIZE.sp
            caretHeight = DEFAULT_CARET_HEIGHT.dp
        }

        Text(
            text = text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h3,
            fontSize = fontSize,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.hasVisualOverflow && fontSize > MIN_FONT_SIZE.sp) {
                    fontSize = (fontSize.value - 2).sp
                    caretHeight = (caretHeight.value - 2.6).dp
                }
            }
        )
        BlinkingCaret(height = caretHeight)
    }
}