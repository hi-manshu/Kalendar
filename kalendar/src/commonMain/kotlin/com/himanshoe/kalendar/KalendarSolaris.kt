package com.himanshoe.kalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.himanshoe.kalendar.foundation.KalendarScaffold
import com.himanshoe.kalendar.foundation.action.KalendarSelectedDayRange
import com.himanshoe.kalendar.foundation.action.OnDaySelectionAction
import com.himanshoe.kalendar.foundation.action.onDayClick
import com.himanshoe.kalendar.foundation.color.KalendarColor
import com.himanshoe.kalendar.foundation.component.KalendarDay
import com.himanshoe.kalendar.foundation.component.KalendarHeader
import com.himanshoe.kalendar.foundation.component.config.KalendarDayKonfig
import com.himanshoe.kalendar.foundation.component.config.KalendarDayLabelKonfig
import com.himanshoe.kalendar.foundation.component.config.KalendarKonfig
import com.himanshoe.kalendar.foundation.event.KalendarEvents
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

@Composable
internal fun KalendarSolaris(
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
    events: KalendarEvents = KalendarEvents(),
    showDayLabel: Boolean = true,
    onDaySelectionAction: OnDaySelectionAction = OnDaySelectionAction.Single { _, _ -> },
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    startDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
) {
    KalendarSolarisContent(
        selectedDate = selectedDate,
        startDayOfWeek = startDayOfWeek,
        modifier = modifier,
        arrowShown = false,
        backgroundColor = kalendarKonfig.backgroundColor,
        showDayLabel = showDayLabel,
        onDaySelectionAction = onDaySelectionAction,
        dayKonfig = kalendarKonfig.kalendarDayKonfig,
        kalendarDayLabelKonfig = kalendarKonfig.kalendarDayLabelKonfig,
        events = events,
    )
}

@Composable
private fun KalendarSolarisContent(
    selectedDate: LocalDate,
    startDayOfWeek: DayOfWeek,
    arrowShown: Boolean,
    backgroundColor: KalendarColor,
    showDayLabel: Boolean,
    onDaySelectionAction: OnDaySelectionAction,
    dayKonfig: KalendarDayKonfig,
    events: KalendarEvents,
    kalendarDayLabelKonfig: KalendarDayLabelKonfig,
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
    val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }
    var rangeStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var rangeEndDate by remember { mutableStateOf<LocalDate?>(null) }
    var clickedNewDate by remember { mutableStateOf(selectedDate) }
    val daysOfWeek = DayOfWeek.entries.rotate(startDayOfWeek.ordinal)
    val displayDates by remember(currentMonth, startDayOfWeek) {
        mutableStateOf(getMonthDates(currentMonth, startDayOfWeek))
    }
    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )
    val coroutineScope = rememberCoroutineScope()
    val calendarIconEnabled = pagerState.currentPage != Int.MAX_VALUE / 2

    Column(
        modifier = modifier.background(brush = Brush.linearGradient(colors = backgroundColor.value)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KalendarHeader(
            modifier = Modifier,
            month = currentMonth.month,
            year = currentMonth.year,
            showCalendarIcon = true,
            arrowShown = arrowShown,
            calendarIconEnabled = calendarIconEnabled,
            onNavigateToday = {
                if (calendarIconEnabled) {
                    coroutineScope.launch {
                        currentMonth = today
                        pagerState.animateScrollToPage(page = Int.MAX_VALUE / 2)
                    }
                }
            }
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) {
            KalendarScaffold(
                modifier = Modifier.fillMaxWidth(),
                showDayLabel = showDayLabel,
                dayOfWeek = { daysOfWeek },
                kalendarDayLabelKonfig = kalendarDayLabelKonfig,
                dates = { displayDates },
            ) { date ->
                if (date.month == currentMonth.month) {
                    KalendarDay(
                        modifier = Modifier,
                        date = date,
                        selectedRange = selectedRange.value,
                        onDayClick = { clickedDate, events ->
                            clickedDate.onDayClick(
                                events = events,
                                rangeStartDate = rangeStartDate,
                                rangeEndDate = rangeEndDate,
                                onDaySelectionAction = onDaySelectionAction,
                                onClickedNewDate = {
                                    clickedNewDate = it
                                },
                                onClickedRangeStartDate = {
                                    rangeStartDate = it
                                },
                                onClickedRangeEndDate = {
                                    rangeEndDate = it
                                },
                                onUpdateSelectedRange = {
                                    selectedRange.value = it
                                },
                            )
                        },
                        dayKonfig = dayKonfig,
                        events = events,
                        selectedDate = clickedNewDate,
                    )
                } else {
                    Box(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
    LaunchedEffect(pagerState.currentPage) {
        val startDate = selectedDate.plus(
            value = (pagerState.currentPage - Int.MAX_VALUE / 2),
            unit = DateTimeUnit.MONTH
        )
        currentMonth = startDate
    }
}
