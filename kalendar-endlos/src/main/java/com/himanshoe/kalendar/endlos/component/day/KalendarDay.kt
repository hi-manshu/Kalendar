package com.himanshoe.kalendar.endlos.component.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayConfig
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayState
import com.himanshoe.kalendar.endlos.component.text.KalendarNormalText
import com.himanshoe.kalendar.endlos.model.KalendarDay
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import kotlinx.datetime.LocalDate

@Composable
fun KalendarDay(
    kalendarDay: KalendarDay,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    textSize: TextUnit = 16.sp,
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
    kalendarEvents: List<KalendarEvent> = emptyList(),
    isCurrentDay: Boolean = false,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    selectedKalendarDay: LocalDate,
    isSelected: Boolean,
) {
    val kalendarDayState = getKalendarDayState(selectedKalendarDay, kalendarDay.localDate, isSelected)
    val backgroundColor = getBackgroundColor(kalendarDayState, kalendarDayConfig, kalendarDay.localDate.month.value)
    val textColor = getTextColor(kalendarDayState, kalendarDayConfig)
    val shape = getTextSelectionShape(kalendarDayState)
    val weight = getTextWeight(kalendarDayState)
    val border = getBorder(isCurrentDay, kalendarDayConfig)

    Column(
        modifier = modifier
            .border(border = border, shape = CircleShape)
            .clip(shape = shape)
            .size(size = size)
            .background(color = backgroundColor)
            .clickable { onCurrentDayClick(kalendarDay, kalendarEvents) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KalendarNormalText(
            text = kalendarDay.localDate.dayOfMonth.toString(),
            modifier = Modifier,
            fontWeight = weight,
            textSize = textSize,
            color = textColor,
        )
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            if (kalendarEvents.isNotEmpty()) {
                kalendarEvents.take(3).forEachIndexed { index, _ ->
                    KalendarDots(
                        modifier = Modifier,
                        index = index,
                        kalendarDay = kalendarDay,
                        size = size,
                        kalendarDayConfig = kalendarDayConfig
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyKalendarDay(
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    background: Color,
) {

    Box(
        modifier = modifier
            .clip(shape = RectangleShape)
            .size(size = size)
            .background(
                color = background
            ),
    )
}

private fun getKalendarDayState(
    selectedDate: LocalDate,
    currentDay: LocalDate,
    isSelected: Boolean
) =
    if (isSelected) {
        KalendarDayState.KalendarDaySelected
    } else {
        when (selectedDate) {
            currentDay -> KalendarDayState.KalendarDaySelected
            else -> KalendarDayState.KalendarDayDefault
        }
    }

private fun getBorder(isCurrentDay: Boolean, kalendarDayConfig: KalendarDayConfig) =
    BorderStroke(
        width = if (isCurrentDay) 1.dp else 0.dp,
        color = if (isCurrentDay) kalendarDayConfig.kalendarDayColors.currentDayBorderColor else Color.Transparent,
    )

private fun getTextWeight(kalendarDayState: KalendarDayState) =
    if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
        FontWeight.SemiBold
    } else {
        FontWeight.Normal
    }

private fun getBackgroundColor(
    kalendarDayState: KalendarDayState,
    kalendarDayConfig: KalendarDayConfig,
    month: Int
) = if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
    kalendarDayConfig.kalendarDayColors.getBackgroundColor(month = month)
} else {
    Color.Transparent
}

private fun getTextSelectionShape(
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
}

@Composable
fun KalendarDots(
    modifier: Modifier = Modifier,
    kalendarDayConfig: KalendarDayConfig,
    index: Int,
    kalendarDay: KalendarDay,
    size: Dp
) {
    Box(
        modifier = modifier
            .padding(horizontal = 1.dp)
            .clip(shape = CircleShape)
            .background(
                color = kalendarDayConfig.kalendarDayColors
                    .getEventColor(
                        kalendarDay.localDate.month.value
                    )
                    .copy(alpha = index.plus(1) * 0.3F)
            )
            .size(size = size.div(12))
    )
}
