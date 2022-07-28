package com.himanshoe.kalendar.endlos.ui
/*
* MIT License
*
* Copyright (c) 2022 Himanshu Singh
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.endlos.common.KalendarKonfig
import com.himanshoe.kalendar.endlos.common.KalendarSelector
import com.himanshoe.kalendar.endlos.common.data.KalendarEvent
import com.himanshoe.kalendar.endlos.common.theme.Grid
import com.himanshoe.kalendar.endlos.util.CalendarMonthSource.getInitialMonths
import com.himanshoe.kalendar.endlos.util.CalendarMonthSource.next6Months
import com.himanshoe.kalendar.endlos.util.InfiniteLoadingList
import java.time.LocalDate
import java.time.YearMonth

@Composable
internal fun KalendarView(
    yearMonth: YearMonth = YearMonth.now(),
    selectedDay: LocalDate,
    kalendarSelector: KalendarSelector,
    kalendarEvents: List<KalendarEvent>,
    kalendarKonfig: KalendarKonfig,
    onCurrentDayClick: (LocalDate, List<KalendarEvent>) -> Unit,
    errorMessageLogged: (String) -> Unit,
    dateRangeEnabled: Boolean = false
) {
    val monthsList: MutableList<Int> = getInitialMonths() as MutableList<Int>

    val monthsState = remember { mutableStateOf(monthsList) }
    val clickedDay: MutableState<LocalDate> = remember {
        mutableStateOf(selectedDay)
    }
    InfiniteLoadingList(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Grid.Half),
        items = monthsState.value,
        loadMore = {
            val year = yearMonth.plusMonths(monthsState.value.last().toLong()).year
            val newMonths = next6Months(monthsState.value.last())
            if (kalendarKonfig.maxYear == 0) {
                monthsList.addAll(newMonths)
                monthsState.value = monthsList
            } else if (kalendarKonfig.maxYear != 0 && year <= kalendarKonfig.maxYear) {
                newMonths.filter {
                    yearMonth.plusMonths(it.toLong()).year <= kalendarKonfig.maxYear
                }
                monthsList.addAll(newMonths)
                monthsState.value = monthsList
            } else {
                errorMessageLogged("Max limit reached")
            }
        }
    ) { _, item ->
        val month = item as Int

        KalendarMonth(
            selectedDay,
            yearMonth.plusMonths(month.toLong()),
            onCurrentDayClick,
            kalendarSelector,
            kalendarEvents,
            kalendarKonfig,
            clickedDay,
            dateRangeEnabled
        )
    }
}
