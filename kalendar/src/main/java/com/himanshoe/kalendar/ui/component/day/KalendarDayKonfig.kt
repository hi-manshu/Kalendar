package com.himanshoe.kalendar.ui.component.day

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class KalendarDayKonfig(
    val size: Dp,
    val textSize: TextUnit
) {
    companion object {
        fun default() = KalendarDayKonfig(
            size = 56.dp, textSize = 16.sp,
        )
    }
}

