package com.himanshoe.kalendarkit.config

import androidx.compose.ui.graphics.Color

internal val backgroundColor = listOf(
    Color.White,
    Color(0xFFFCEFFE),
    Color(0xFFFDF2FE),
    Color(0xFFFEF7FE),
    Color(0xFFF9FDFE),
    Color(0xFFF1FEFF),
    Color(0xFFEBFEFF),
    Color(0xFFE9FEFF),
    Color(0xFFEBFEFF),
    Color(0xFFFCFFFC),
    Color(0xFFFFFFFB),
    Color(0xFFFFFFF7),
)

data class KalendarConfigs(
    val background: Color
) {
    fun getBackground(month: Int) = backgroundColor[month.minus(1)]
}

object KalendarConfigDefaults {

    fun kalendarConfigDefaults(month: Int = 1) =
        KalendarConfigs(background = backgroundColor[month.minus(1)])

}

