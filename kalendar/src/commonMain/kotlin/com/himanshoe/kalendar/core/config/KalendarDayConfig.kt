package com.himanshoe.kalendar.core.config

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.core.color.KalendarColor
import com.himanshoe.kalendar.core.color.asSolidChartColor

data class KalendarDayKonfig(
    val size: Dp,
    val textSize: TextUnit,
    val textColor: KalendarColor,
    val selectedTextColor: KalendarColor,
    val borderColor: KalendarColor,
    val indicatorColor: KalendarColor,
) {
    companion object {

        fun default() = KalendarDayKonfig(
            size = 56.dp,
            textSize = 16.sp,
            textColor = Color(0xFF413D4B).asSolidChartColor(),
            indicatorColor = Color(0xFFD8A29E).asSolidChartColor(),
            selectedTextColor = Color(0xFF413D4B).asSolidChartColor(),
            borderColor = Color(0xFFC39EA1).asSolidChartColor(),
        )
    }
}