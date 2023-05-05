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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.himanshoe.kalendar.ui.component.day.KalendarDayColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarHeader
import com.himanshoe.kalendar.ui.component.header.KalendarTextConfig
import com.himanshoe.kalendar.ui.oceanic.util.getNext7Dates
import com.himanshoe.kalendar.ui.oceanic.util.getPrevious7Dates
import com.himanshoe.kalendar.util.MultiplePreview
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun KalendarOceanic(
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextConfig: KalendarTextConfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    kalendarDayColors: KalendarDayColors = KalendarDayColors.default(),
    dayContent: (@Composable () -> Unit)? = null,
    dayLabelContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable () -> Unit)? = null,
) {
    val today = currentDay ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val weekValue = remember { mutableStateOf(today.getNext7Dates()) }
    val month = weekValue.value.first().month
    val year = weekValue.value.first().year
    val selectedDate = remember { mutableStateOf(today) }
    val currentMonthIndex = month.value.minus(1)

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
            headerContent()
        } else {
            KalendarHeader(
                month = month,
                year = year,
                kalendarTextConfig = kalendarHeaderTextConfig ?: KalendarTextConfig(
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
                            if (dayLabelContent != null) {
                                dayLabelContent(item)
                            } else {
                                Text(
                                    modifier = Modifier,
                                    color = kalendarDayColors.textColor,
                                    fontSize = kalendarDayKonfig.textSize,
                                    text = item.dayOfWeek.getDisplayName(
                                        TextStyle.FULL, Locale.getDefault()
                                    ).take(1),
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        if (dayContent != null) {
                            dayContent()
                        } else {
                            KalendarDay(
                                date = item,
                                selectedDate = selectedDate.value,
                                kalendarColors = kalendarColors.color[currentMonthIndex],
                                kalendarEvents = events,
                                kalendarDayColors = kalendarDayColors,
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

@Composable
fun KalendarOceanic(
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextConfig: KalendarTextConfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    kalendarDayColors: KalendarDayColors = KalendarDayColors.default(),
    dayContent: (@Composable () -> Unit)? = null,
    dayLabelContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable () -> Unit)? = null,
) {
    KalendarOceanic(
        currentDay = currentDay,
        modifier = modifier,
        showLabel = showLabel,
        kalendarHeaderTextConfig = kalendarHeaderTextConfig,
        kalendarColors = kalendarColors,
        onDayClick = onDayClick,
        events = KalendarEvents(),
        kalendarDayKonfig = kalendarDayKonfig,
        kalendarDayColors = kalendarDayColors,
        dayContent = dayContent,
        dayLabelContent = dayLabelContent,
        headerContent = headerContent,
    )
}

@MultiplePreview
@Composable
fun KalendarOceanicPreview() {
    KalendarOceanic(
        currentDay = Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ),
        kalendarHeaderTextConfig = KalendarTextConfig.previewDefault()
    )
}
