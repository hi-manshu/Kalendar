package com.himanshoe.kalendar.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.primitive.texts.Medium
import com.himanshoe.design.primitive.texts.Regular
import com.himanshoe.design.theme.KalendarShape
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.YearRange
import com.himanshoe.kalendar.common.ui.KalendarDot
import java.time.LocalDate

@Composable
internal fun KalendarOceanWeek(
    startDate: LocalDate = LocalDate.now(),
    selectedDay: LocalDate = startDate,
    yearRange: YearRange,
    onCurrentDayClick: (LocalDate) -> Unit,
    errorMessageLogged: (String) -> Unit,
    kalendarSelector: KalendarSelector,
) {
    val isDot = kalendarSelector is KalendarSelector.Dot
    val haptic = LocalHapticFeedback.current
    val displayWeek = remember {
        mutableStateOf(startDate.getNext7Dates())
    }
    val clickedDate = remember {
        mutableStateOf(selectedDay)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val size = (maxWidth / 7)
        val monthName = "${displayWeek.value.last().month.name} ${displayWeek.value.last().year}"
        Column(Modifier.fillMaxWidth()) {

            KalendarOceanHeader(monthName, displayWeek, haptic, yearRange, errorMessageLogged)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                displayWeek.value.forEach { date ->
                    val isSelected = date == clickedDate.value

                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        KalendarText.Body1.Regular(
                            text = date.dayOfWeek.toString().subSequence(0, 3).toString(),
                            modifier = Modifier
                                .width(size)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center
                        )
                        KalendarText.H4.Medium(
                            text = date.dayOfMonth.toString(),
                            color = if (isSelected) kalendarSelector.selectedTextColor else kalendarSelector.defaultTextColor,
                            modifier = Modifier
                                .size(size)
                                .clip(if (isDot) KalendarShape.DefaultRectangle else kalendarSelector.shape)
                                .background(
                                    if (!isDot) {
                                        getColor(
                                            kalendarSelector = kalendarSelector,
                                            selectedDate = clickedDate.value,
                                            providedDate = date
                                        )
                                    } else Color.Transparent
                                )
                                .clickable {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    clickedDate.value = date
                                    onCurrentDayClick(date)
                                }
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center
                        )
                        if (isDot) {
                            KalendarDot(kalendarSelector = kalendarSelector,
                                isSelected = isSelected,
                                isToday = date == LocalDate.now())
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun getColor(
    selectedDate: LocalDate,
    providedDate: LocalDate,
    kalendarSelector: KalendarSelector,
): Color {
    return if (providedDate == LocalDate.now()) {
        when (selectedDate) {
            providedDate -> kalendarSelector.selectedColor
            else -> kalendarSelector.todayColor
        }
    } else {
        when (selectedDate) {
            providedDate -> kalendarSelector.selectedColor
            else -> kalendarSelector.defaultColor
        }
    }
}
