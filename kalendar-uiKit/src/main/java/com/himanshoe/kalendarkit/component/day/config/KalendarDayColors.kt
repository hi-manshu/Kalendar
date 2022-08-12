package com.himanshoe.kalendarkit.component.day.config

import androidx.compose.ui.graphics.Color

internal val PrimaryColor = Color(0xFFD2827A)
internal val SecondaryColor = Color(0xFF413D4B)
internal val TextColor = Color(0xFF413D4B)

data class KalendarDayColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val selectedColor: Color = primaryColor.copy(alpha = 0.5F),
    val backgroundColor: Color = selectedColor.copy(alpha = 0.5F),
    val textColor: Color,
    val selectedTextColor: Color
)

internal object KalendarDayColorsDefaults {

    fun kalendarDayColors() = KalendarDayColors(
        primaryColor = PrimaryColor,
        secondaryColor = SecondaryColor,
        textColor = TextColor,
        selectedTextColor = Color.White
    )
}


