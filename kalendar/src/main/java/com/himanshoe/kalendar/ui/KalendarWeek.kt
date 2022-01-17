package com.himanshoe.kalendar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

const val DAYS_IN_WEEK = 7

@Composable
internal fun KalendarWeek(
    startColumn: Int, // 0..6
    endColumn: Int,   // 0..6 && startColumn <= endColumn
    fromDay: Int,    // 1..31
    currentYear: Int,
    currentMonth: Int,
    todayYear: Int,
    todayMonth: Int,
    todayDayOfMonth: Int,
    selectedYear: Int,
    selectedMonth: Int,
    selectedDayOfMonth: Int,
    onDateClick: (Int, Int, Int) -> Unit,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {

        val size = maxWidth / DAYS_IN_WEEK

        val modifier = Modifier
            .requiredWidth(size)
            .requiredHeight(size)
            .graphicsLayer(clip = false)

        var currentDay = fromDay

        Row {
            repeat(DAYS_IN_WEEK) { index: Int ->
                if (index in startColumn..endColumn) {
                    val isSelected =
                        currentYear == selectedYear && currentMonth == selectedMonth && currentDay == selectedDayOfMonth
                    KalendarDay(
                        size = size,
                        modifier = modifier,
                        day = currentDay,
                        isSelected = isSelected,
                        isToday = currentYear == todayYear && currentMonth == todayMonth && currentDay == todayDayOfMonth,
                        onDayClick = { day: Int ->
                            onDateClick(currentYear, currentMonth, day)
                        }
                    )
                    currentDay++
                } else {
                    KalendarEmptyDay(modifier = modifier)
                }
            }
        }
    }
}
