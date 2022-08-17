package com.himanshoe.kalendar.component.day.config

import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.endloss.color.KalendarColors

internal val TextColor = Color(0xFF413D4B)
internal val BackgroundColor = Color(0xFFFEF9F7)
internal val SelectedColor = Color(0xFFF4B6B0)
internal val CurrentDayBorderColor = TextColor

data class KalendarDayColors(
    val selectedBackgroundColor: Color,
    val backgroundColor: Color,
    val textColor: Color,
    val selectedTextColor: Color,
    val currentDayBorderColor: Color
) {
    fun getEventColor(month: Int) = KalendarColors.headerColors[month.minus(1)]
}

internal object KalendarDayColorsDefaults {

    fun kalendarDayColors() = KalendarDayColors(
        textColor = TextColor,
        selectedBackgroundColor = SelectedColor,
        backgroundColor = BackgroundColor,
        selectedTextColor = Color.White,
        currentDayBorderColor = CurrentDayBorderColor,
    )
}
