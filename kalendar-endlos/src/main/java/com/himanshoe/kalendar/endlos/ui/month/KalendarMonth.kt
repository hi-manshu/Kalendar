package com.himanshoe.kalendar.endlos.ui.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.himanshoe.kalendar.endlos.DaySelectionMode
import com.himanshoe.kalendar.endlos.KalendarDates
import com.himanshoe.kalendar.endlos.RangeSelectionError
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.ui.color.KalendarColor
import com.himanshoe.kalendar.endlos.ui.day.KalendarDay
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.time.format.TextStyle
import java.util.Locale


@Composable
internal fun KalendarMonth(
    kalendarDates: KalendarDates,
    month: Month,
    year: Int,
    events: KalendarEvents,
    kalendarColor: KalendarColor,
    contentPadding: PaddingValues,
    kalendarDayKonfig: KalendarDayKonfig,
    kalendarHeaderTextKonfig: KalendarTextKonfig?,
    modifier: Modifier = Modifier,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    dayContent: @Composable() ((LocalDate) -> Unit)? = null,
    headerContent: @Composable() ((Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val selectedDate = remember { mutableStateOf(today) }
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }

    Column(
        modifier = modifier
            .padding(contentPadding)
            .background(kalendarColor.backgroundColor)
    ) {
        if (headerContent != null) {
            headerContent(month, year)
        } else {
            kalendarHeaderTextKonfig?.let {
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp)
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    color = it.kalendarTextColor,
                    fontSize = it.kalendarTextSize,
                    text = getTitleText(month, year),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
            }
        }

        kalendarDates.dates.fastForEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                week.fastForEach { date ->
                    date?.let { nonNullDate ->
                        if (dayContent != null) {
                            dayContent(nonNullDate)
                        } else {
                            KalendarDay(
                                date = nonNullDate,
                                selectedDate = selectedDate.value,
                                selectedRange = selectedRange.value,
                                events = events,
                                onDayClick = { clickedDate, event ->
                                    onDayClicked(
                                        clickedDate,
                                        event,
                                        daySelectionMode,
                                        selectedRange,
                                        onRangeSelected = { range, events ->
                                            if (range.end < range.start) {
                                                onErrorRangeSelected(RangeSelectionError.EndIsBeforeStart)
                                            } else {
                                                onRangeSelected(range, events)
                                            }
                                        },
                                        onDayClick = { newDate, clickedDateEvent ->
                                            selectedDate.value = newDate
                                            onDayClick(clickedDate, clickedDateEvent)
                                        }
                                    )
                                },
                                kalendarDayKonfig = kalendarDayKonfig,
                                kalendarColor = kalendarColor,
                            )
                        }
                    } ?: run {
                        Box(modifier = Modifier.size(56.dp))
                    }
                }
            }
        }
    }
}

private fun onDayClicked(
    date: LocalDate,
    events: List<KalendarEvent>,
    daySelectionMode: DaySelectionMode,
    selectedRange: MutableState<KalendarSelectedDayRange?>,
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> }
) {
    when (daySelectionMode) {
        DaySelectionMode.Single -> {
            onDayClick(date, events)
        }

        DaySelectionMode.Range -> {
            val range = selectedRange.value
            selectedRange.value = if (range?.isEmpty() != false) {
                KalendarSelectedDayRange(start = date, end = date)
            } else if (range.isSingleDate()) {
                KalendarSelectedDayRange(start = range.start, end = date)
            } else {
                KalendarSelectedDayRange(start = date, end = date)
            }
            selectedRange.value?.let { selectedRange ->
                val selectedEvents = events
                    .filter { it.date in (selectedRange.start..selectedRange.end) }
                    .toList()

                onRangeSelected(selectedRange, selectedEvents)
            }
        }
    }
}

private fun getTitleText(month: Month, year: Int): String {
    val monthDisplayName = month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val shortYear = year.toString().takeLast(2)
    return "$monthDisplayName '$shortYear"
}
