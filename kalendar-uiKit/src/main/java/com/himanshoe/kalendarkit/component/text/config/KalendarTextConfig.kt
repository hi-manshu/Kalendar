package com.himanshoe.kalendarkit.component.text.config

data class KalendarTextConfig(
    val kalendarTextColor: KalendarTextColor,
    val kalendarTextSize: KalendarTextSize
)

internal object KalendarTextDefaults {

    fun kalendarTitleTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarTitleTextColor(),
            kalendarTextSize = KalendarTextSize.Title
        )

    fun kalendarSubTitleTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarTitleTextColor(),
            kalendarTextSize = KalendarTextSize.SubTitle
        )

    fun kalendarNormalTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarNormalTextColor(),
            kalendarTextSize = KalendarTextSize.Normal
        )
}
