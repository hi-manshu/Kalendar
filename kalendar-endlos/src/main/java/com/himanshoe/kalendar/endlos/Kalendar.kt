package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.paging.KalendarPagingController
import com.himanshoe.kalendar.endlos.paging.rememberKalendarPagingController
import com.himanshoe.kalendar.endlos.ui.color.KalendarColors
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    pagingController: KalendarPagingController = rememberKalendarPagingController(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    contentPadding: PaddingValues = PaddingValues(8.dp),
    monthContentPadding: PaddingValues = PaddingValues(4.dp),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    weekValueContent: (@Composable () -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    onDayClicked: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    KalendarEndlos(
        modifier = modifier,
        showLabel = showLabel,
        pagingController = pagingController,
        kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
        kalendarColors = kalendarColors,
        onDayClicked = onDayClicked,
        events = events,
        kalendarDayKonfig = kalendarDayKonfig,
        contentPadding = contentPadding,
        monthContentPadding = monthContentPadding,
        dayContent = dayContent,
        weekValueContent = weekValueContent,
        daySelectionMode = daySelectionMode,
        onRangeSelected = onRangeSelected,
        onErrorRangeSelected = onErrorRangeSelected,
        headerContent = headerContent
    )
}
