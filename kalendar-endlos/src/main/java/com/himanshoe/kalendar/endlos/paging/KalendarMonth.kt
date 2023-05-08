package com.himanshoe.kalendar.endlos.paging

import com.ankitsuda.rebound.ui.calendar.models.CalendarDay
import java.time.LocalDate
import java.time.YearMonth

data class KalendarMonth(
    val yearMonth: YearMonth,
    val weekDays: List<List<LocalDate>>,
    internal val indexInSameMonth: Int,
    internal val numberOfSameMonth: Int
)