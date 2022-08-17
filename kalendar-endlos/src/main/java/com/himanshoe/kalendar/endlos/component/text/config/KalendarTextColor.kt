package com.himanshoe.kalendar.endlos.component.text.config

import androidx.compose.ui.graphics.Color

private val TitleTextColor = Color(0xFFD2827A)

data class KalendarTextColor(
    val textColor: Color
)

internal object KalendarTextColorDefaults {

    fun kalendarTitleTextColor() = KalendarTextColor(
        textColor = TitleTextColor
    )
    fun kalendarNormalTextColor() = KalendarTextColor(
        textColor = TitleTextColor
    )
}
