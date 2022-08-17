package com.himanshoe.kalendarkit.component.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendarkit.component.day.config.KalendarDayState
import com.himanshoe.kalendarkit.component.text.KalendarNormalText
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent
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
) {
    val kalendarDayState = getKalendarDayState(selectedKalendarDay, kalendarDay.localDate)
    val backgroundColor = getBackgroundColor(kalendarDayState, kalendarDayConfig)
    val textColor = getTextColor(kalendarDayState, kalendarDayConfig)
    val shape = getTextSelectionShape(kalendarDayState)
    val weight = getTextWeight(kalendarDayState)
    val border = getBorder(isCurrentDay, kalendarDayConfig)

    Box(
        modifier = modifier
            .border(border = border, shape = CircleShape)
            .clip(shape = shape)
            .size(size = size)
            .background(color = backgroundColor)
            .clickable {
                onCurrentDayClick(kalendarDay, kalendarEvents)
            },
        contentAlignment = Alignment.Center
    ) {
        KalendarNormalText(
            text = kalendarDay.localDate.dayOfMonth.toString(),
            modifier = Modifier,
            fontWeight = weight,
            textSize = textSize,
            color = textColor,
        )
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

private fun getKalendarDayState(selectedDate: LocalDate, currentDay: LocalDate) =
    when (selectedDate) {
        currentDay -> KalendarDayState.KalendarDaySelected
        else -> KalendarDayState.KalendarDayDefault
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
    kalendarDayConfig: KalendarDayConfig
) = if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
    kalendarDayConfig.kalendarDayColors.selectedBackgroundColor
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
//    KalendarDay(
//        kalendarDay = KalendarDay(localDate = Clock.System.todayIn(TimeZone.currentSystemDefault())),
//        kalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
//        selectedKalendarDate = mutableStateOf<LocalDate>()
//    )
}
