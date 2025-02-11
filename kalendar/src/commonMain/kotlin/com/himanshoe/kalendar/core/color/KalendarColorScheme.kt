package com.himanshoe.kalendar.core.color

import androidx.compose.ui.graphics.Color

data class KalendarColorScheme(
    val backgroundColor: KalendarColor,
    val dayBackgroundColor: KalendarColor,
    val headerTextColor: Color,
) {
    companion object {
        fun default(): KalendarColorScheme {
            return KalendarColorScheme(
                backgroundColor = KalendarColor.Solid(Color(0xFFFEF9F7)),
                dayBackgroundColor = KalendarColor.Solid(Color(0xFFF7CFD3)),
                headerTextColor = Color(0xFFC39EA1)
            )
        }
    }
}