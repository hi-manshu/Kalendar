package com.himanshoe.kalendarkit.component.day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendarkit.component.day.config.KalendarDayState
import com.himanshoe.kalendarkit.model.KalendarDay
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun KalendarDay(
    kalendarDay: KalendarDay,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    kalendarDayState: KalendarDayState = KalendarDayState.KalendarDayDefault,
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig()
) {
    val backgroundColor = kalendarDayConfig.kalendarDayColors.backgroundColor
    val textColor = getTextColor(kalendarDayState, kalendarDayConfig)
    val shape = getTextSelectionShape(kalendarDayState)

    Box(
        modifier = modifier
            .clip(shape = shape)
            .size(size)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            color = textColor,
            text = kalendarDay.localDate.dayOfMonth.toString(),
            textAlign = TextAlign.Center
        )
    }
}

fun getTextSelectionShape(
    kalendarDayState: KalendarDayState,
) = if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
    CircleShape
} else {
    RectangleShape
}

private fun getTextColor(
    kalendarDayState: KalendarDayState,
    kalendarDayConfig: KalendarDayConfig
): Color = if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
    kalendarDayConfig.kalendarDayColors.selectedTextColor
} else {
    kalendarDayConfig.kalendarDayColors.textColor
}

@Preview
@Composable
private fun KalendarDayPreview() {
    KalendarDay(
        kalendarDay = KalendarDay(localDate = Clock.System.todayIn(TimeZone.currentSystemDefault())),
        kalendarDayConfig = KalendarDayDefaults.kalendarDayConfig()
    )
}
