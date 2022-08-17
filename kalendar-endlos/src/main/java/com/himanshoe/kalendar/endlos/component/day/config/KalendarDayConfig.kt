package com.himanshoe.kalendar.endlos.component.day.config

data class KalendarDayConfig(
    val kalendarDayColors: KalendarDayColors
)

internal object KalendarDayDefaults {

    fun kalendarDayConfig() = KalendarDayConfig(kalendarDayColors = KalendarDayColorsDefaults.kalendarDayColors())
}
