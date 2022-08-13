package com.himanshoe.kalendarkit.component.text.config

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class KalendarTextSize(val size: TextUnit) {
    object Title : KalendarTextSize(32.sp)
    object SubTitle : KalendarTextSize(24.sp)
    object Normal : KalendarTextSize(16.sp)
    data class Custom(val textUnit: TextUnit) : KalendarTextSize(textUnit)
}
