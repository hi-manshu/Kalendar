package com.himanshoe.kalendar.component.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.component.day.config.KalendarDayState
import com.himanshoe.kalendar.component.text.KalendarNormalText
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import kotlinx.datetime.LocalDate

@Composable
fun KalendarDay(
    kalendarDay: KalendarDay,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    textSize: TextUnit = 16.sp,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    isCurrentDay: Boolean = false,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    selectedKalendarDay: LocalDate,
    kalendarDayColors: KalendarDayColors,
    dotColor: Color,
    dayBackgroundColor: Color,
) {
    val kalendarDayState = getKalendarDayState(selectedKalendarDay, kalendarDay.localDate)
    val bgColor = getBackgroundColor(kalendarDayState, dayBackgroundColor)
    val textColor = getTextColor(kalendarDayState, kalendarDayColors)
    val weight = getTextWeight(kalendarDayState)
    val border = getBorder(isCurrentDay)

    Column(
        modifier = modifier
            .border(border = border, shape = CircleShape)
            .clip(shape = CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) { onCurrentDayClick(kalendarDay, kalendarEvents) }
            .size(size = size)
            .background(color = bgColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KalendarNormalText(
            text = kalendarDay.localDate.dayOfMonth.toString(),
            modifier = Modifier,
            fontWeight = weight,
            color = textColor,
            textSize = textSize,
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
                        size = size,
                        color = dotColor
                    )
                }
            }
        }
    }
}

@Composable
fun KalendarDots(
    modifier: Modifier = Modifier,
    index: Int,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .padding(horizontal = 1.dp)
            .clip(shape = CircleShape)
            .background(
                color = color
                    .copy(alpha = index.plus(1) * 0.3F)
            )
            .size(size = size.div(12))
    )
}

private fun getKalendarDayState(selectedDate: LocalDate, currentDay: LocalDate) =
    when (selectedDate) {
        currentDay -> KalendarDayState.KalendarDaySelected
        else -> KalendarDayState.KalendarDayDefault
    }

private fun getBorder(isCurrentDay: Boolean) =
    BorderStroke(
        width = if (isCurrentDay) 1.dp else 0.dp,
        color = if (isCurrentDay) Color.Black else Color.Transparent,
    )

private fun getTextWeight(kalendarDayState: KalendarDayState) =
    if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
        FontWeight.Bold
    } else {
        FontWeight.SemiBold
    }

private fun getBackgroundColor(
    kalendarDayState: KalendarDayState,
    backgroundColor: Color,
) = if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
    backgroundColor
} else {
    Color.Transparent
}

private fun getTextSelectionShape(
    kalendarDayState: KalendarDayState,
) = if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
    CircleShape
} else {
    CircleShape
}

private fun getTextColor(
    kalendarDayState: KalendarDayState,
    kalendarDayColors: KalendarDayColors,
): Color = if (kalendarDayState is KalendarDayState.KalendarDaySelected) {
    kalendarDayColors.selectedTextColor
} else {
    kalendarDayColors.textColor
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
