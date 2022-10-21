package by.coolightman.calculator.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun DragElement(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .height(4.dp)
            .width(50.dp)
            .clip(RoundedCornerShape(size = 2.dp))
            .background(MaterialTheme.colors.secondaryVariant)
    )
}