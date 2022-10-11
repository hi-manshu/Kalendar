package com.himanshoe.kalendar.component.text.config

import androidx.compose.ui.graphics.Color

private val TitleTextColor = Color(0xFFD2827A)

data class KalendarTextColor(
    val textColor: Color
)

internal object KalendarTextColorDefaults {

    fun kalendarTitleTextColor(color: Color = TitleTextColor) = KalendarTextColor(
        textColor = color
    )

    fun kalendarNormalTextColor(color: Color = TitleTextColor) = KalendarTextColor(
        textColor = color
    )
}
