package com.himanshoe.kalendar.foundation.component.config

import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.foundation.color.KalendarColor

data class KalendarKonfig(
    val kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    val kalendarHeaderKonfig: KalendarHeaderKonfig = KalendarHeaderKonfig.default(),
    val kalendarDayLabelKonfig: KalendarDayLabelKonfig = KalendarDayLabelKonfig.default(),
    val backgroundColor: KalendarColor = KalendarColor.Solid(Color.White),
)
