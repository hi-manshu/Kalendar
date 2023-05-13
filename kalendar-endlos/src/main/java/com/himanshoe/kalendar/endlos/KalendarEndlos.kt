package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.paging.KalendarModelEntity
import com.himanshoe.kalendar.endlos.paging.KalendarPagingController
import com.himanshoe.kalendar.endlos.paging.rememberKalendarPagingController
import com.himanshoe.kalendar.endlos.ui.color.KalendarColor
import com.himanshoe.kalendar.endlos.ui.color.KalendarColors
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

private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun KalendarEndlos(
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    pagingController: KalendarPagingController = rememberKalendarPagingController(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    contentPadding: PaddingValues = PaddingValues(8.dp),
    monthContentPadding: PaddingValues = PaddingValues(4.dp),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    weekValueContent: (@Composable () -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
) {
    val kalendarItems = pagingController.kalendarItems.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding,
        content = {
            if (weekValueContent != null) {
                stickyHeader {
                    weekValueContent()
                }
            } else {
                if (showLabel) {
                    stickyHeader {
                        KalendarStickerHeader(
                            kalendarDayKonfig.textColor,
                            kalendarDayKonfig.textSize
                        )
                    }
                }
            }
            items(
                count = kalendarItems.itemCount,
                key = kalendarItems.itemKey(),
                contentType = kalendarItems.itemContentType()
            ) { index ->
                val calendarModel: KalendarModelEntity? = kalendarItems[index]
                val dates: List<List<LocalDate?>>? = calendarModel?.dates?.chunked(7)
                if (dates != null) {
                    val currentMonthIndex = calendarModel.month.value.minus(1)
                    val headerTextKonfig = kalendarHeaderTextKonfig ?: KalendarTextKonfig(
                        kalendarTextColor = kalendarColors.color[currentMonthIndex].headerTextColor,
                        kalendarTextSize = 24.sp
                    )

                    KalendarMonth(
                        kalendarDates = dates.toKalendarDates(),
                        month = calendarModel.month,
                        year = calendarModel.year,
                        contentPadding = monthContentPadding,
                        dayContent = dayContent,
                        kalendarDayKonfig = kalendarDayKonfig,
                        onDayClick = onDayClick,
                        events = events,
                        kalendarHeaderTextKonfig = headerTextKonfig,
                        headerContent = headerContent,
                        kalendarColor = kalendarColors.color[currentMonthIndex],
                    )
                }
            }
        }
    )
}

@Composable
private fun KalendarStickerHeader(color: Color, textSize: TextUnit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            repeat(WeekDays.size) {
                Text(
                    modifier = Modifier
                        .weight(1F),
                    color = color,
                    fontSize = textSize,
                    text = WeekDays[it],
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

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
    dayContent: @Composable ((LocalDate) -> Unit)? = null,
    headerContent: @Composable ((Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
) {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val selectedDate = remember { mutableStateOf(today) }

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
                                events = events,
                                onDayClick = { date, event ->
                                    selectedDate.value = date
                                    onDayClick(date, event)
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

private fun getTitleText(month: Month, year: Int): String {
    val monthDisplayName = month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val shortYear = year.toString().takeLast(2)
    return "$monthDisplayName '$shortYear"
}

internal data class KalendarDates(val dates: List<List<LocalDate?>>)

internal fun List<List<LocalDate?>>.toKalendarDates() = KalendarDates(this)
