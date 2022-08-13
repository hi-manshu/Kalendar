package com.himanshoe.kalendarkit.config

import androidx.compose.ui.graphics.Color

private val KalendarBackground = Color(0xFFFEF9F7)

data class KalendarConfigs(
    val background: Color
)

object KalendarConfigDefaults {

    fun kalendarConfigDefaults() = KalendarConfigs(background = KalendarBackground)
}
