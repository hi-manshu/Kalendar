package com.himanshoe.kalendar.foundation.component.config

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class KalendarDayLabelKonfig(
    val textStyle: TextStyle,
    val textCharCount: Int,
    val centerAligned: Boolean
) {
    companion object {
        fun default() = KalendarDayLabelKonfig(
            centerAligned = true,
            textCharCount = 2,
            textStyle = TextStyle(
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF613D4B),
                        Color(0xFFD8A26E)
                    )
                ),
            ),
        )
    }
}
