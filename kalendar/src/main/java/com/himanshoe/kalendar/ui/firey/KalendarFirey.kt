package com.himanshoe.kalendar.ui.firey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDay
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarHeader
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.oceanic.util.isLeapYear
import com.himanshoe.kalendar.util.MultiplePreviews
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn

private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
internal fun KalendarFirey(
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
    val monthValue = currentMonth.value.toString().padStart(2, '0')
    val startDayOfMonth = "$currentYear-$monthValue-01".toLocalDate()
    val firstDayOfMonth = startDayOfMonth.dayOfWeek

    Column(
        modifier = modifier
            .background(
                color = kalendarColors.color[currentMonthIndex].backgroundColor
            )
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        if (headerContent != null) {
            headerContent(currentMonth, currentYear)
        } else {
            KalendarHeader(
                month = currentMonth,
                year = currentYear,
                kalendarTextKonfig = newHeaderTextKonfig,
                onPreviousClick = {
                    displayedYear.value -= if (currentMonth == Month.JANUARY) 1 else 0
                    displayedMonth.value -= 1
                },
                onNextClick = {
                    displayedYear.value += if (currentMonth == Month.DECEMBER) 1 else 0
                    displayedMonth.value += 1
                },
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            content = {
                if (showLabel) {
                    items(WeekDays) { item ->
                        Text(
                            modifier = Modifier,
                            color = kalendarDayKonfig.textColor,
                            fontSize = kalendarDayKonfig.textSize,
                            text = item,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                items((getFirstDayOfMonth(firstDayOfMonth)..daysInMonth).toList()) {
                    if (it > 0) {
                        val day = calculateDay(it, currentMonth, currentYear)
                        if (dayContent != null) {
                            dayContent(day)
                        } else {
                            KalendarDay(
                                date = day,
                                selectedDate = selectedDate.value,
                                kalendarColors = kalendarColors.color[currentMonthIndex],
                                kalendarEvents = events,
                                kalendarDayKonfig = kalendarDayKonfig,
                                onDayClick = { date, event ->
                                    selectedDate.value = date
                                    onDayClick(date, event)
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

private fun getFirstDayOfMonth(firstDayOfMonth: DayOfWeek) = -(firstDayOfMonth.value).minus(2)

private fun calculateDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue = currentMonth.value.toString().padStart(2, '0')
    val dayValue = day.toString().padStart(2, '0')
    return "$currentYear-$monthValue-$dayValue".toLocalDate()
}

@Composable
@MultiplePreviews
private fun KalendarFireyPreview() {
    KalendarFirey(
        currentDay = Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ),
        kalendarHeaderTextKonfig = KalendarTextKonfig.previewDefault()
    )
}
