package com.himanshoe.kalendar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private const val DAYS_IN_WEEK = 7

@Composable
internal fun KalendarMonth(
    yearMonth: YearMonth = YearMonth.now(),
    onCurrentDayClick: (LocalDate) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        val monthState = remember {
            mutableStateOf(yearMonth)
        }
        val selectedDay = remember {
            mutableStateOf(LocalDate.now())
        }
        val formatter = remember {
            DateTimeFormatter.ofPattern(
                if (Year.now().value == monthState.value.year) "MMMM" else "MMMM y"
            )
        }
        KalendarHeader(
            text = monthState.value.format(formatter),
            onPreviousMonthClick = {
                monthState.value = monthState.value.minusMonths(1)
            },
            onNextMonthClick = {
                monthState.value = monthState.value.plusMonths(1)
            },
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
                            val isSelected = monthState.value.month == selectedDay.value.month &&
                                    monthState.value.year == selectedDay.value.year &&
                                    localDate == selectedDay.value

                            KalendarDay(
                                size = size,
                                date = localDate,
                                isSelected = isSelected,
                                isToday = localDate == LocalDate.now(),
                                onDayClick = { date ->
                                    selectedDay.value = date
                                    onCurrentDayClick(date)
                                }
                            )
                        } else {
                            KalendarEmptyDay(modifier = Modifier.size(size))
                        }
                    }
                }
            }
        }
    }
}

fun getDays(monthState: MutableState<YearMonth>): List<LocalDate> {
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
