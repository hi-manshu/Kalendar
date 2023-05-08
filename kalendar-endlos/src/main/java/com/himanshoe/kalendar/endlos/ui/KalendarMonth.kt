package com.himanshoe.kalendar.endlos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.endlos.model.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.model.KalendarTextKonfig
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn
import java.time.Month

private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
fun KalendarMonth(
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
) {
    val today = currentDay ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val selectedDate = remember { mutableStateOf(today) }
    val displayedMonth = remember { mutableStateOf(today.month) }
    val displayedYear = remember { mutableStateOf(today.year) }
    val currentMonth = displayedMonth.value
    val currentYear = displayedYear.value
    val currentMonthIndex = currentMonth.value.minus(1)

    val newHeaderTextKonfig = kalendarHeaderTextKonfig ?: KalendarTextKonfig(
        kalendarTextColor = kalendarColors.color[currentMonthIndex].headerTextColor,
        kalendarTextSize = 24.sp
    )

    val daysInMonth = currentMonth.length(currentYear.isLeapYear())
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0" + currentMonth.value.toString() else currentMonth.value.toString()
    val startDayOfMonth = "$currentYear-$monthValue-01".toLocalDate()
    val firstDayOfMonth = startDayOfMonth.dayOfWeek

}


fun Int.isLeapYear(): Boolean {
    val year = this
    return when {
        ((year % 400 == 0) || (((year % 4) == 0) && ((year % 100) != 0))) -> true
        else -> false
    }
}

private fun getFirstDayOfMonth(firstDayOfMonth: DayOfWeek) = -(firstDayOfMonth.value).minus(2)

private fun calculateDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0${currentMonth.value}" else currentMonth.value.toString()
    val newDay = if (day.toString().length == 1) "0$day" else day
    return "$currentYear-$monthValue-$newDay".toLocalDate()
}