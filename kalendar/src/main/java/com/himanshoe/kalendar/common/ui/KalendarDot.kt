package com.himanshoe.kalendar.common.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.design.theme.Grid
import com.himanshoe.kalendar.common.KalendarSelector

@Composable
internal fun KalendarDot(
    isSelected: Boolean,
    isToday: Boolean,
    kalendarSelector: KalendarSelector,
) {
    Surface(shape = kalendarSelector.shape,
        color = getColor(isSelected, isToday, kalendarSelector),
        modifier = Modifier
            .size(Grid.One), content = {})
}

private fun getColor(
    isSelected: Boolean,
    isToday: Boolean,
    kalendarSelector: KalendarSelector,
): Color {
    return when {
        isToday -> kalendarSelector.todayColor
        isSelected -> kalendarSelector.selectedColor
        else -> kalendarSelector.defaultColor
    }
}


