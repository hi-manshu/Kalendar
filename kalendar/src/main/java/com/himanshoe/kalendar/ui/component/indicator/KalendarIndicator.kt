package com.himanshoe.kalendar.ui.component.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Composable function that renders a Kalendar indicator.
 *
 * @param index The index of the indicator.
 * @param size The size of the indicator.
 * @param color The color of the indicator.
 * @param modifier The modifier for the indicator.
 */
@Composable
fun KalendarIndicator(
    index: Int,
    size: Dp,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 1.dp)
            .clip(shape = CircleShape)
            .background(color = color.copy(alpha = (index + 1) * 0.3f))
            .size(size = size.div(12))
    )
}
