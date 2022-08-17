package com.himanshoe.kalendarkit.component.text.config

data class KalendarTextConfig(
    val kalendarTextColor: KalendarTextColor = KalendarTextColorDefaults.kalendarTitleTextColor(),
    val kalendarTextSize: KalendarTextSize = KalendarTextSize.Title
)

internal object KalendarTextDefaults {

    fun kalendarTitleTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarTitleTextColor(),
            kalendarTextSize = KalendarTextSize.Title
        )

    fun kalendarNormalTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarNormalTextColor(),
            kalendarTextSize = KalendarTextSize.Normal
        )
}
