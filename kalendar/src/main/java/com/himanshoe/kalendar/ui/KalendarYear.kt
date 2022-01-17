package com.himanshoe.kalendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarShape
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.DayOfWeek
import com.himanshoe.kalendar.config.KalendarConfig
import com.himanshoe.kalendar.getDayOfWeek
import java.util.*

@Composable
private fun KalendarView(
    currentYear: Int,
    currentMonth: Int,
    currentMonthNumberOfDays: Int,
    todayYear: Int,
    todayMonth: Int,
    todayDayOfMonth: Int,
    selectedYear: Int,
    selectedMonth: Int,
    selectedDayOfMonth: Int,
    startOfMonthDayOfWeek: DayOfWeek,
    onCurrentDayClick: (Int, Int, Int) -> Unit,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Grid.Half)
    ) {
        KalendarHeader(
            currentYear = currentYear,
            currentMonth = currentMonth,
            onPreviousMonthClick = onPreviousMonthClick,
            onNextMonthClick = onNextMonthClick
        )
        KalendarWeekDayNames()
        KalendarMonth(
            currentYear = currentYear,
            currentMonth = currentMonth,
            currentMonthNumberOfDays = currentMonthNumberOfDays,
            todayYear = todayYear,
            todayMonth = todayMonth,
            todayDayOfMonth = todayDayOfMonth,
            selectedYear = selectedYear,
            selectedMonth = selectedMonth,
            selectedDayOfMonth = selectedDayOfMonth,
            startOfMonthDayOfWeek = startOfMonthDayOfWeek,
            onDateClick = onCurrentDayClick
        )
    }
}

@Composable
fun Kalendar(
    kalendarConfig: KalendarConfig = KalendarConfig()
) {
    val currentCalendarStartOfMonth = rememberSaveable {
        Calendar.getInstance().apply {
            set(Calendar.DATE, 1)
        }
    }
    val currentDate = rememberSaveable { Calendar.getInstance() }
    var currentYear by rememberSaveable { mutableStateOf(currentDate.get(Calendar.YEAR)) }
    var currentMonth by rememberSaveable { mutableStateOf(currentDate.get(Calendar.MONTH)) }

    val currentMonthNumberOfDays =
        rememberSaveable { currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) }

    val todayDate = Calendar.getInstance()
    val todayYear = todayDate.get(Calendar.YEAR)
    val todayMonth = todayDate.get(Calendar.MONTH)
    val todayDayOfMonth = todayDate.get(Calendar.DAY_OF_MONTH)

    var selectedYear by rememberSaveable { mutableStateOf(todayYear) }
    var selectedMonth by rememberSaveable { mutableStateOf(todayMonth) }
    var selectedDayOfMonth by rememberSaveable { mutableStateOf(currentDate.get(Calendar.DAY_OF_MONTH)) }
    val haptic = LocalHapticFeedback.current

    KalendarTheme {
        val color = kalendarConfig.backgroundColor ?: KalendarTheme.colors.generalDisabled
        val calendarBackgroundColor =
            kalendarConfig.calendarColor ?: KalendarTheme.colors.background
        val shape =
            if (kalendarConfig.hasRadius) KalendarShape.SelectedShape else KalendarShape.DefaultRectangle
        Card(
            modifier = Modifier
                .background(color)
                .padding(Grid.OneHalf),
            shape = shape,
            elevation = if (kalendarConfig.hasRadius) Grid.One else 0.dp,
            backgroundColor = calendarBackgroundColor,
        ) {
            KalendarView(
                currentYear = currentYear,
                currentMonth = currentMonth,
                currentMonthNumberOfDays = currentMonthNumberOfDays,
                todayYear = todayYear,
                todayMonth = todayMonth,
                todayDayOfMonth = todayDayOfMonth,
                selectedYear = selectedYear,
                selectedMonth = selectedMonth,
                selectedDayOfMonth = selectedDayOfMonth,
                startOfMonthDayOfWeek = getDayOfWeek(currentCalendarStartOfMonth.get(Calendar.DAY_OF_WEEK)),
                onCurrentDayClick = { year: Int, month: Int, day: Int ->
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    selectedYear = year
                    selectedMonth = month
                    selectedDayOfMonth = day
                },
                onPreviousMonthClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    currentDate.add(Calendar.MONTH, -1)
                    currentCalendarStartOfMonth.add(Calendar.MONTH, -1)
                    currentMonth = currentDate.get(Calendar.MONTH)
                    currentYear = currentDate.get(Calendar.YEAR)
                },
                onNextMonthClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    currentDate.add(Calendar.MONTH, 1)
                    currentCalendarStartOfMonth.add(Calendar.MONTH, 1)
                    currentMonth = currentDate.get(Calendar.MONTH)
                    currentYear = currentDate.get(Calendar.YEAR)
                }
            )
        }
    }
}
