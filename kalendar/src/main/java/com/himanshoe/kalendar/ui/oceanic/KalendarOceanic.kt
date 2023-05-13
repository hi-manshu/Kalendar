package com.himanshoe.kalendar.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.himanshoe.kalendar.ui.oceanic.util.getNext7Dates
import com.himanshoe.kalendar.ui.oceanic.util.getPrevious7Dates
import com.himanshoe.kalendar.util.MultiplePreviews
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun KalendarOceanic(
    modifier: Modifier = Modifier,
    currentDay: LocalDate? = null,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    events: KalendarEvents = KalendarEvents(),
    labelFormat: (DayOfWeek) -> String = {
        it.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        )
    },
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
) {
    val today = currentDay ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val weekValue = remember { mutableStateOf(today.getNext7Dates()) }
    val yearAndMonth = getCurrentMonthAndYear(weekValue.value)
    var selectedDate by remember { mutableStateOf(today) }
    val currentMonthIndex = yearAndMonth.first.value.minus(1)

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
            headerContent(yearAndMonth.first, yearAndMonth.second)
        } else {
            KalendarHeader(
                month = yearAndMonth.first,
                year = yearAndMonth.second,
                kalendarTextKonfig = kalendarHeaderTextKonfig ?: KalendarTextKonfig(
                    kalendarTextColor = kalendarColors.color[currentMonthIndex].headerTextColor,
                    kalendarTextSize = 24.sp
                ),
                onPreviousClick = {
                    val firstDayOfDisplayedWeek = weekValue.value.first()
                    weekValue.value = firstDayOfDisplayedWeek.getPrevious7Dates()
                },
                onNextClick = {
                    val lastDayOfDisplayedWeek = weekValue.value.last().plus(1, DateTimeUnit.DAY)
                    weekValue.value = lastDayOfDisplayedWeek.getNext7Dates()
                },
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            content = {
                itemsIndexed(weekValue.value) { index, item ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (showLabel) {
                            Text(
                                modifier = Modifier,
                                color = kalendarDayKonfig.textColor,
                                fontSize = kalendarDayKonfig.textSize,
                                text = labelFormat(item.dayOfWeek),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        if (dayContent != null) {
                            dayContent(item)
                        } else {
                            KalendarDay(
                                date = item,
                                selectedDate = selectedDate,
                                kalendarColors = kalendarColors.color[currentMonthIndex],
                                kalendarEvents = events,
                                kalendarDayKonfig = kalendarDayKonfig,
                                onDayClick = { date, event ->
                                    selectedDate = date
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

private fun getCurrentMonthAndYear(weekValue: List<LocalDate>): Pair<Month, Int> {
    val month = weekValue.first().month
    val year = weekValue.first().year
    return Pair(month, year)
}

@MultiplePreviews
@Composable
fun KalendarOceanicPreview() {
    KalendarOceanic(
        currentDay = Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ),
        kalendarHeaderTextKonfig = KalendarTextKonfig.previewDefault()
    )
}
