package by.coolightman.calculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.calculator.ui.theme.CalculaTorTheme

@Composable
fun HistoryRowItem(
    expression: String,
    result: String
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = expression,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface.copy(0.6f),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = result,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    CalculaTorTheme {
        HistoryRowItem(expression = "2+2X2", result = "208759")
    }
}