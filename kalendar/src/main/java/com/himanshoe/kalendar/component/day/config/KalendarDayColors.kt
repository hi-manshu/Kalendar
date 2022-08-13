package com.himanshoe.kalendar.component.day.config

import androidx.compose.ui.graphics.Color

internal val PrimaryColor = Color(0xFFD2827A)
internal val SecondaryColor = Color(0xFF413D4B)
internal val TextColor = Color(0xFF413D4B)
internal val BackgroundColor = Color(0xFFFEF9F7)
internal val SelectedColor = Color(0xFFF4B6B0)
internal val CurrentDayBorderColor = TextColor

data class KalendarDayColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val selectedBackgroundColor: Color,
    val backgroundColor: Color,
    val textColor: Color,
    val selectedTextColor: Color,
    val currentDayBorderColor: Color
)

internal object KalendarDayColorsDefaults {

    fun kalendarDayColors() = KalendarDayColors(
        primaryColor = PrimaryColor,
        secondaryColor = SecondaryColor,
        textColor = TextColor,
        selectedBackgroundColor = SelectedColor,
        backgroundColor = BackgroundColor,
        selectedTextColor = Color.White,
        currentDayBorderColor = CurrentDayBorderColor,

        )
}


