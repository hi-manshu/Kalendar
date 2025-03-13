package com.himanshoe.kalendar.core.config

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.core.color.KalendarColor
import com.himanshoe.kalendar.core.color.asSolidChartColor

data class KalendarDayKonfig(
    val size: Dp,
    val selectedTextColor: KalendarColor,
    val borderColor: KalendarColor,
    val indicatorColor: KalendarColor,
    val textStyle: TextStyle,
    val selectedBackgroundColor: KalendarColor,
) {
    companion object {

        fun default() = KalendarDayKonfig(
            size = 56.dp,
            textStyle = TextStyle(
                fontSize = 16.sp,
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF413D4B),
                        Color(0xFFD8A29E)
                    )
                ),
            ),
            indicatorColor = Color(0xFFD8A29E).asSolidChartColor(),
            selectedTextColor = Color(0xFF413D4B).asSolidChartColor(),
            borderColor = Color(0xFFC39EA1).asSolidChartColor(),
            selectedBackgroundColor = Color(0xFFF7CFD3).asSolidChartColor(),
        )
    }
}