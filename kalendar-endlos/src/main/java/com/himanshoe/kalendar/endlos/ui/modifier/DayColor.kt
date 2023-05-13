package com.himanshoe.kalendar.endlos.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import kotlinx.datetime.LocalDate

private const val FULL_ALPHA = 1f
private const val TOWNED_DOWN_ALPHA = 0.4F

/**
 * A modifier that applies the background color to a composable based on the day's selection state,
 * selected range, and specified color.
 *
 * @param selected Whether the day is selected.
 * @param color The color to be applied as the background.
 * @param date The date corresponding to the day.
 * @param selectedRange The selected date range.
 *
 * @return The modified modifier.
 */
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

