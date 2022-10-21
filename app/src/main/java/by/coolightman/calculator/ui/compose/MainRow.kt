package by.coolightman.calculator.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.coolightman.calculator.util.RESULT_MAX_LENGTH

private const val MIN_FONT_SIZE = 20
private const val DEFAULT_FONT_SIZE = 48

@Composable
fun MainRow(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        var fontSize by remember {
            mutableStateOf(DEFAULT_FONT_SIZE.sp)
        }

        var caretHeight by remember {
            mutableStateOf((fontSize.value * 1.1).dp)
        }

        LaunchedEffect(key1 = text.isEmpty() || text.length <= RESULT_MAX_LENGTH) {
            fontSize = DEFAULT_FONT_SIZE.sp
            caretHeight = (fontSize.value * 1.1).dp
        }

        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h3,
            fontSize = fontSize,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.hasVisualOverflow && fontSize > MIN_FONT_SIZE.sp) {
                    fontSize = (fontSize.value - 2).sp
                    caretHeight = (fontSize.value * 1.1).dp
                }
            }
        )
        BlinkingCaret(height = caretHeight)
    }
}