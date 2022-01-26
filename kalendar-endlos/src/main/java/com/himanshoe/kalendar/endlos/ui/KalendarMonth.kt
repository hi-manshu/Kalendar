package com.himanshoe.kalendar.endlos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.endlos.common.data.KalendarEvent
import com.himanshoe.kalendar.endlos.util.getMonthNameFormatter
import java.time.LocalDate
import java.time.YearMonth

private const val DAYS_IN_WEEK = 7

@Composable
internal fun KalendarMonth(
    selectedDay: LocalDate,
    yearMonth: YearMonth = YearMonth.now(),
    onCurrentDayClick: (LocalDate, KalendarEvent?) -> Unit,
    kalendarSelector: KalendarSelector,
    kalendarEvents: List<KalendarEvent>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        val haptic = LocalHapticFeedback.current

        val monthState = remember {
            mutableStateOf(yearMonth)
        }
        val clickedDay = remember {
            mutableStateOf(selectedDay)
        }

        KalendarHeader(
            kalendarSelector = kalendarSelector,
            text = monthState.value.format(getMonthNameFormatter()),
        )
        KalendarWeekDayNames()

        val days: List<LocalDate> = getDays(monthState)

        days.chunked(DAYS_IN_WEEK).forEach { weekDays ->
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val size = (maxWidth / DAYS_IN_WEEK)
                Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    weekDays.forEach { localDate ->
                        val isFromCurrentMonth = YearMonth.from(localDate) == monthState.value
                        if (isFromCurrentMonth) {
                            val isSelected = monthState.value.month == clickedDay.value.month &&
                                    monthState.value.year == clickedDay.value.year &&
                                    localDate == clickedDay.value

                            KalendarDay(
                                size = size,
                                date = localDate,
                                isSelected = isSelected,
                                isToday = localDate == LocalDate.now(),
                                kalendarSelector = kalendarSelector,
                                kalendarEvents = kalendarEvents,
                                onDayClick = { date, event ->
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    clickedDay.value = date
                                    onCurrentDayClick(date, event)
                                }
                            )
                        } else {
                            KalendarEmptyDay(modifier = Modifier.size(size))
                        }
                    }
                }
            }
        }

        Divider(color = kalendarSelector.selectedColor,
            modifier = Modifier.alpha(0.5F),
            thickness = 1.dp)
    }
}

private fun getDays(monthState: MutableState<YearMonth>): List<LocalDate> {
    return mutableListOf<LocalDate>().apply {
        val firstDay = monthState.value.atDay(1)
        val firstSunday = if (firstDay.dayOfWeek == java.time.DayOfWeek.SUNDAY) {
            firstDay
        } else {
            firstDay.minusDays(firstDay.dayOfWeek.value.toLong())
        }
        repeat(6) { weekIndex ->
            (0..6).forEach { dayIndex ->
                add(firstSunday.plusDays((7 * weekIndex + dayIndex).toLong()))
            }
        }
    }
}