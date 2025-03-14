package com.himanshoe.kalendar.foundation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.foundation.color.KalendarColor

@Composable
fun KalendarIndicator(
    index: Int,
    size: Dp,
    color: KalendarColor,
    modifier: Modifier = Modifier,
) {
    val brush = Brush.linearGradient(color.value.map { it.copy(alpha = (index + 1) * 0.3f) })
    Box(
        modifier = modifier
            .padding(horizontal = 1.dp)
            .clip(shape = CircleShape)
            .background(brush = brush)
            .size(size = size.div(12))
    )
}