package com.himanshoe.kalendar.ui.component.day.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import kotlinx.datetime.LocalDate


private const val FULL_ALPHA = 1f
private const val TOWNED_DOWN_ALPHA = 0.4F

fun Modifier.dayBackgroundColor(
    selected: Boolean,
    color: Color,
    date: LocalDate,
    selectedRange: KalendarSelectedDayRange?
): Modifier {
    val inRange = date == selectedRange?.start || date == selectedRange?.end

    val backgroundColor = when {
        selected -> color
        selectedRange != null && date in selectedRange.start..selectedRange.end -> {
            val alpha = if (inRange) FULL_ALPHA else TOWNED_DOWN_ALPHA
            color.copy(alpha = alpha)
        }

        else -> Color.Transparent
    }

    return this.then(
        background(backgroundColor)
    )
}


