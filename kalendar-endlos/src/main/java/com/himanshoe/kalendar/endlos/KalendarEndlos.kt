package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.paging.KalendarModelEntity
import com.himanshoe.kalendar.endlos.paging.KalendarPagingController
import com.himanshoe.kalendar.endlos.paging.rememberKalendarPagingController
import com.himanshoe.kalendar.endlos.ui.month.KalendarMonth
import com.himanshoe.kalendar.endlos.ui.color.KalendarColors
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun KalendarEndlos(
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
    daySelectionMode: DaySelectionMode = DaySelectionMode.Range,
    onDayClicked: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
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
                        onDayClick = onDayClicked,
                        events = events,
                        kalendarHeaderTextKonfig = headerTextKonfig,
                        headerContent = headerContent,
                        kalendarColor = kalendarColors.color[currentMonthIndex],
                        daySelectionMode = daySelectionMode,
                        onRangeSelected = onRangeSelected,
                        onErrorRangeSelected = onErrorRangeSelected
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

internal data class KalendarDates(val dates: List<List<LocalDate?>>)

internal fun List<List<LocalDate?>>.toKalendarDates() = KalendarDates(this)
