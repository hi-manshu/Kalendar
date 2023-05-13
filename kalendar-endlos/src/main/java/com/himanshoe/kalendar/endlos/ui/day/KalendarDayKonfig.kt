package com.himanshoe.kalendar.endlos.ui.day

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Configuration data class for customizing the appearance of a Kalendar day.
 *
 * @param size The size of the Kalendar day.
 * @param textSize The text size of the day.
 * @param textColor The text color of the day.
 * @param selectedTextColor The text color of the selected day.
 * @param borderColor The border color of the day.
 */
@Stable
data class KalendarDayKonfig(
    val size: Dp,
    val textSize: TextUnit,
    val textColor: Color,
    val selectedTextColor: Color,
    val borderColor: Color = Color.Black
) {
    companion object {

        internal fun default() = KalendarDayKonfig(
            size = 56.dp,
            textSize = 16.sp,
            textColor = Color(0xFF413D4B),
            selectedTextColor = Color.White
        )
    }
}
