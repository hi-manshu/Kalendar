package com.himanshoe.kalendarkit.ui.oceanic.config

import androidx.compose.ui.graphics.Color

private val OceanicBackground = Color(0xFFFEF9F7)

data class KalendarOceanicConfigs(
    val background: Color
)

object KalendarOceanicConfigsDefaults {

    fun kalendarOceanicConfigsDefaults() = KalendarOceanicConfigs(background = OceanicBackground)
}
