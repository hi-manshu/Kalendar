package com.himanshoe.kalendar.endlos.component.text.config

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class KalendarTextSize(val size: TextUnit) {
    object Title : KalendarTextSize(28.sp)
    object Normal : KalendarTextSize(16.sp)
    data class Custom(val textUnit: TextUnit) : KalendarTextSize(textUnit)
}
