package com.himanshoe.kalendar.config

import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.color.KalendarColors

data class KalendarConfigs(
    val background: Color? = null
) {
    fun getBackground(month: Int) = KalendarColors.kalendarBackgroundColor[month.minus(1)]
}

object KalendarConfigDefaults {

    fun kalendarConfigDefaults(month: Int = 1) =
        KalendarConfigs(
            background =
            KalendarColors.kalendarBackgroundColor[month.minus(1)]
        )
}
