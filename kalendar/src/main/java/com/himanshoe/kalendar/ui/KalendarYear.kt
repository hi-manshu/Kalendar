package com.himanshoe.kalendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.DayOfWeek
import com.himanshoe.kalendar.getDayOfWeek
import java.util.*

@Composable
private fun CalendarScreen(
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
    onDateClick: (Int, Int, Int) -> Unit,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
            onDateClick = onDateClick
        )
    }
}

@Composable
fun Kalendar() {
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

    var selectedYear by rememberSaveable { mutableStateOf(todayDate.get(Calendar.YEAR)) }
    var selectedMonth by rememberSaveable { mutableStateOf(todayDate.get(Calendar.MONTH)) }
    var selectedDayOfMonth by rememberSaveable { mutableStateOf(currentDate.get(Calendar.DAY_OF_MONTH)) }
    KalendarTheme {
        Box(
            modifier = Modifier
                .background(KalendarTheme.colors.background)
                .padding(Grid.OneHalf)
        ) {
            CalendarScreen(
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
                onDateClick = { year: Int, month: Int, day: Int ->
                    selectedYear = year
                    selectedMonth = month
                    selectedDayOfMonth = day
                },
                onPreviousMonthClick = {
                    currentDate.add(Calendar.MONTH, -1)
                    currentCalendarStartOfMonth.add(Calendar.MONTH, -1)
                    currentMonth = currentDate.get(Calendar.MONTH)
                    currentYear = currentDate.get(Calendar.YEAR)
                },
                onNextMonthClick = {
                    currentDate.add(Calendar.MONTH, 1)
                    currentCalendarStartOfMonth.add(Calendar.MONTH, 1)
                    currentMonth = currentDate.get(Calendar.MONTH)
                    currentYear = currentDate.get(Calendar.YEAR)
                }
            )
        }
    }
}
