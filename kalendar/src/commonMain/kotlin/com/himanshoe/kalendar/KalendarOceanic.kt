package com.himanshoe.kalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.kalendar.core.color.KalendarColorScheme
import com.himanshoe.kalendar.core.color.asSolidChartColor
import com.himanshoe.kalendar.core.component.KalendarDay
import com.himanshoe.kalendar.core.component.KalendarHeader
import com.himanshoe.kalendar.core.config.KalendarDayKonfig
import com.himanshoe.kalendar.core.util.KalendarSelectedDayRange
import com.himanshoe.kalendar.event.KalendarEvent
import com.himanshoe.kalendar.event.KalendarEvents
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

@Composable
fun KalendarOceanic(
    selectedDate: LocalDate,
    arrowShown: Boolean,
    colorScheme: KalendarColorScheme,
    showDayLabel: Boolean,
    dayKonfig: KalendarDayKonfig,
    events: KalendarEvents,
    modifier: Modifier = Modifier,
    startDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
) {
    KalendarOceanicContent(
        selectedDate = selectedDate,
        startDayOfWeek = startDayOfWeek,
        modifier = modifier,
        arrowShown = arrowShown,
        colorScheme = colorScheme,
        showDayLabel = showDayLabel,
        onRangeSelected = onRangeSelected,
        onDayClick = onDayClick,
        dayKonfig = dayKonfig,
        events = events
    )
}

@Composable
private fun KalendarOceanicContent(
    selectedDate: LocalDate,
    startDayOfWeek: DayOfWeek,
    arrowShown: Boolean,
    colorScheme: KalendarColorScheme,
    showDayLabel: Boolean,
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit,
    dayKonfig: KalendarDayKonfig,
    events: KalendarEvents,
    modifier: Modifier = Modifier,
) {
    var currentMonth by remember {
        mutableStateOf(
            selectedDate.minus(
                selectedDate.dayOfMonth - 1,
                DateTimeUnit.DAY
            )
        )
    }
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }
    var rangeStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var rangeEndDate by remember { mutableStateOf<LocalDate?>(null) }
    var clickedNewDate by remember { mutableStateOf(selectedDate) }
    val daysOfWeek = DayOfWeek.values().rotate(startDayOfWeek.ordinal)
    val displayDates by remember(currentMonth, startDayOfWeek) {
        mutableStateOf(generateMonthDates(currentMonth, startDayOfWeek))
    }

    Column(
        modifier = modifier.background(brush = Brush.linearGradient(colorScheme.backgroundColor.value)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KalendarHeader(
            modifier = Modifier,
            month = currentMonth.month,
            year = currentMonth.year,
            arrowShown = arrowShown,
            colorScheme = colorScheme,
            onPreviousClick = { currentMonth = currentMonth.minus(1, DateTimeUnit.MONTH) },
            onNextClick = { currentMonth = currentMonth.plus(1, DateTimeUnit.MONTH) }
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.Center,
            content = {
                if (showDayLabel) {
                    items(daysOfWeek) { dayOfWeek ->
                        Text(
                            text = dayOfWeek.name.take(1),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                items(displayDates) { date ->
                    val isPreviousMonth = date.month != currentMonth.month
                    val newDayKonfig = if (isPreviousMonth) {
                        dayKonfig.copy(textColor = Color.LightGray.asSolidChartColor())
                    } else {
                        dayKonfig
                    }
                    KalendarDay(
                        date = date,
                        selectedRange = selectedRange.value,
                        onDayClick = { clickedDate, events ->
                            if (rangeStartDate == null || rangeEndDate != null) {
                                rangeStartDate = clickedDate
                                rangeEndDate = null
                            } else {
                                rangeEndDate = clickedDate
                                if (rangeStartDate != null && rangeEndDate != null) {
                                    if (rangeStartDate!! > rangeEndDate!!) {
                                        // Swap the dates if start date is after end date
                                        val temp = rangeStartDate
                                        rangeStartDate = rangeEndDate
                                        rangeEndDate = temp
                                    }
                                    selectedRange.value =
                                        KalendarSelectedDayRange(rangeStartDate!!, rangeEndDate!!)
                                    selectedRange.value?.let { onRangeSelected(it, events) }
                                }
                            }
                            clickedNewDate = clickedDate
                            onDayClick(clickedDate, events)
                        },
                        dayKonfig = newDayKonfig,
                        colorScheme = colorScheme,
                        events = events,
                        selectedDate = clickedNewDate,
                    )
                }
            }
        )
    }
}

private fun generateMonthDates(
    currentMonth: LocalDate,
    startDayOfWeek: DayOfWeek
): List<LocalDate> {
    val firstDayOfMonth = currentMonth.minus(currentMonth.dayOfMonth - 1, DateTimeUnit.DAY)
    val lastDayOfMonth = firstDayOfMonth.plus(1, DateTimeUnit.MONTH).minus(1, DateTimeUnit.DAY)
    val firstDayOffset = (firstDayOfMonth.dayOfWeek.ordinal - startDayOfWeek.ordinal + 7) % 7
    return (-firstDayOffset until lastDayOfMonth.dayOfMonth).map {
        firstDayOfMonth.plus(
            it.toLong(),
            DateTimeUnit.DAY
        )
    }
}

private fun Array<DayOfWeek>.rotate(n: Int): Array<DayOfWeek> {
    return this.drop(n).toTypedArray() + this.take(n).toTypedArray()
}
