package com.himanshoe.kalendar.endlos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.himanshoe.kalendar.endlos.common.KalendarKonfig
import com.himanshoe.kalendar.endlos.common.KalendarSelector
import com.himanshoe.kalendar.endlos.common.data.KalendarEvent
import com.himanshoe.kalendar.endlos.util.getMonthNameFormatter
import java.time.LocalDate
import java.time.YearMonth
import java.util.Collections
import kotlin.math.abs

private const val DaysInWeek = 7
private val WeekList = listOf(7, 1, 2, 3, 4, 5, 6)

@Composable
internal fun KalendarMonth(
    selectedDay: LocalDate,
    yearMonth: YearMonth = YearMonth.now(),
    onCurrentDayClick: (LocalDate, List<KalendarEvent>) -> Unit,
    kalendarSelector: KalendarSelector,
    kalendarEvents: List<KalendarEvent>,
    kalendarKonfig: KalendarKonfig,
    clickedDay: MutableState<LocalDate>,
    dateRangeEnabled: Boolean = false,
    onDateRangeSelected: (Pair<LocalDate, LocalDate>) -> Unit = {}
) {
    val previousClickedDay = remember { mutableStateOf(LocalDate.now()) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        val haptic = LocalHapticFeedback.current
        clickedDay.value = selectedDay
        val monthState = remember {
            mutableStateOf(yearMonth)
        }

        KalendarHeader(
            kalendarSelector = kalendarSelector,
            text = monthState.value.format(getMonthNameFormatter(kalendarKonfig.locale)),
        )
        KalendarWeekDayNames(kalendarKonfig = kalendarKonfig)

        val days: List<LocalDate> = getDays(monthState, kalendarKonfig)

        days.chunked(DaysInWeek).forEach { weekDays ->
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val size = (maxWidth / DaysInWeek)
                Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    weekDays.forEach { localDate ->
                        val isFromCurrentMonth = YearMonth.from(localDate) == monthState.value
                        if (isFromCurrentMonth) {
                            val isSelected =
                                monthState.value.month == clickedDay.value.month && monthState.value.year == clickedDay.value.year && localDate == clickedDay.value
                            val isPreviousDay = monthState.value.month == clickedDay.value.month && monthState.value.year == clickedDay.value.year && localDate == previousClickedDay.value
                            val isBetweenDay = monthState.value.month == clickedDay.value.month && monthState.value.year == clickedDay.value.year && (localDate > previousClickedDay.value && localDate < clickedDay.value || localDate < previousClickedDay.value && localDate > clickedDay.value)
                            KalendarDay(
                                size = size,
                                date = localDate,
                                isSelected = isSelected,
                                isToday = localDate == LocalDate.now(),
                                isPreviousDay = if (dateRangeEnabled) isPreviousDay else false,
                                isBetweenDay = if (dateRangeEnabled) isBetweenDay else false,
                                kalendarSelector = kalendarSelector,
                                kalendarEvents = kalendarEvents,
                                onDayClick = { date, event ->
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    previousClickedDay.value = clickedDay.value
                                    clickedDay.value = date
                                    onCurrentDayClick(date, event)
                                    onDateRangeSelected(previousClickedDay.value to date)
                                }
                            )
                        } else {
                            KalendarEmptyDay(modifier = Modifier.size(size))
                        }
                    }
                }
            }
        }

        Divider(
            color = kalendarSelector.selectedColor,
            modifier = Modifier.alpha(0.5F),
            thickness = 1.dp
        )
    }
}

private fun getDays(monthState: MutableState<YearMonth>, kalendarKonfig: KalendarKonfig): List<LocalDate> {
    return mutableListOf<LocalDate>().apply {
        val firstDay = monthState.value.atDay(1)
        val moves = firstDay.dayOfWeek.value - kalendarKonfig.firstDayOfWeek.value
        val firstDayOfWeek = if (firstDay.dayOfWeek == kalendarKonfig.firstDayOfWeek) {
            firstDay
        } else {
            firstDay.minusDays(firstDay.dayOfWeek.value - kalendarKonfig.firstDayOfWeek.value.toLong())
        }
        when {
            firstDay.dayOfWeek.value > kalendarKonfig.firstDayOfWeek.value -> {
                Collections.rotate(WeekList, moves)
            }
            firstDay.dayOfWeek.value > kalendarKonfig.firstDayOfWeek.value -> {
                Collections.rotate(WeekList, abs(moves))
            }
        }
        repeat(6) { weekIndex ->
            (0..6).forEach { dayIndex ->
                add(firstDayOfWeek.plusDays((7.times(weekIndex).plus(dayIndex)).toLong()))
            }
        }
    }
}
