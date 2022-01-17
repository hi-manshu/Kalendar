package com.himanshoe.kalendar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.himanshoe.kalendar.DayOfWeek
import kotlin.math.ceil

@Composable
fun KalendarMonth(
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
    onDateClick: (Int, Int, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .graphicsLayer(clip = false)

    ) {
        val numberOfRows =
            ceil((currentMonthNumberOfDays + startOfMonthDayOfWeek.value) / DAYS_IN_WEEK.toDouble()).toInt()
        var startDay = 1
        repeat(numberOfRows) {
            val startColumn = if (it == 0) startOfMonthDayOfWeek.value else 0
            val endColumn = if (it != numberOfRows - 1) 6 else currentMonthNumberOfDays - startDay
            KalendarWeek(
                startColumn = startColumn,
                endColumn = endColumn,
                fromDay = startDay,
                currentYear = currentYear,
                currentMonth = currentMonth,
                todayYear = todayYear,
                todayMonth = todayMonth,
                todayDayOfMonth = todayDayOfMonth,
                selectedYear = selectedYear,
                selectedMonth = selectedMonth,
                selectedDayOfMonth = selectedDayOfMonth,
                onDateClick = onDateClick,
            )
            startDay += endColumn - startColumn + 1
        }
    }
}
