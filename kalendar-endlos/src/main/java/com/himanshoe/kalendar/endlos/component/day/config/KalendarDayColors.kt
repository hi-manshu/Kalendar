package com.himanshoe.kalendar.endlos.component.day.config

import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.endlos.color.KalendarColors

internal val TextColor = Color(0xFF413D4B)
internal val CurrentDayBorderColor = TextColor

data class KalendarDayColors(
    val selectedBackgroundColor: Color,
    val textColor: Color, // Default Text Color
    val selectedTextColor: Color, // Selected Text Color
    val currentDayBorderColor: Color,
    val eventColor: Color,
) {
    fun getBackgroundColor(month: Int) = KalendarColors.backgroundColor[month.minus(1)]
    fun getEventColor(month: Int) = KalendarColors.headerColors[month.minus(1)]
}

internal object KalendarDayColorsDefaults {

    fun kalendarDayColors(month: Int = 1) = KalendarDayColors(
        textColor = TextColor,
        selectedBackgroundColor = KalendarColors.backgroundColor[month.minus(1)],
        selectedTextColor = Color.White,
        currentDayBorderColor = CurrentDayBorderColor.copy(alpha = 0.6F),
        eventColor = KalendarColors.headerColors[month.minus(1)],
    )
}
