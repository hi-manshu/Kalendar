package com.himanshoe.kalendar.ui.component.day

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class KalendarDayKonfig(
    val size: Dp,
    val textSize: TextUnit,
    val textColor: Color,
    val selectedTextColor: Color,
) {
    companion object {

        @SuppressWarnings("MagicNumber")
        internal fun default() = KalendarDayKonfig(
            size = 56.dp,
            textSize = 16.sp,
            textColor = Color(0xFF413D4B),
            selectedTextColor = Color.White
        )
    }
}
