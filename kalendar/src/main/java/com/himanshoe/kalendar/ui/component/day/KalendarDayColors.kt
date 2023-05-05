package com.himanshoe.kalendar.ui.component.day

import androidx.compose.ui.graphics.Color

data class KalendarDayColors(
    val textColor: Color,
    val selectedTextColor: Color,
) {
    companion object {
        fun default() = KalendarDayColors(Color(0xFF413D4B), Color.White)
    }
}
