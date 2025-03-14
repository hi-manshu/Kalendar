package com.himanshoe.kalendar.foundation.ext

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import com.himanshoe.kalendar.foundation.action.KalendarSelectedDayRange
import kotlinx.datetime.LocalDate

private const val FULL_ALPHA = 1f
private const val TOWNED_DOWN_ALPHA = 0.5F

fun Modifier.circleLayout() =
    layout { measurable, constraints ->
        // Measure the composable
        val placeable = measurable.measure(constraints)

        // Get the current dimensions
        val height = placeable.height
        val width = placeable.width

        // Calculate the new diameter to make the layout circular
        val diameter = maxOf(height, width)

        // Assign the new dimensions and center the composable
        layout(diameter, diameter) {
            placeable.placeRelative(
                (diameter - width) / 2,
                (diameter - height) / 2
            )
        }
    }

fun Modifier.dayBackgroundColor(
    date: LocalDate,
    selected: Boolean,
    colors: List<Color>,
    selectedRange: KalendarSelectedDayRange?,
): Modifier {
    val inRange = selectedRange?.let { date == it.start || date == it.end } ?: false

    val backgroundBrush = when {
        selected -> Brush.linearGradient(colors)
        selectedRange != null && date in selectedRange.start..selectedRange.end -> {
            val alpha = if (inRange) FULL_ALPHA else TOWNED_DOWN_ALPHA
            Brush.linearGradient(colors.map { it.copy(alpha = alpha) })
        }

        else -> Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Transparent)
        )
    }

    return this.then(
        background(brush = backgroundBrush)
    )
}