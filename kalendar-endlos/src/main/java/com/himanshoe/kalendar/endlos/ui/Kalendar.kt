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
import androidx.compose.runtime.Composable
import com.himanshoe.kalendar.endlos.common.KalendarKonfig
import com.himanshoe.kalendar.endlos.common.KalendarStyle
import com.himanshoe.kalendar.endlos.common.data.KalendarEvent
import java.time.LocalDate

/**
 * [Kalendar] is exposed to be used as composable
 * @param kalendarStyle sets the style of calendar.See [KalendarStyle]
 * @param kalendarKonfig is user to setup config needed for rendering calendar.See [KalendarKonfig]
 * @param selectedDay is representation for selected marker.
 * @param onCurrentDayClick gives the day click listener
 * @param errorMessage gives the error message if any
 */
@Composable
fun Kalendar(
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    kalendarStyle: KalendarStyle = KalendarStyle(),
    selectedDay: LocalDate = LocalDate.now(),
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (LocalDate, KalendarEvent?) -> Unit,
    errorMessage: (String) -> Unit = {},
) {
    KalendarFirey(
        kalendarKonfig = kalendarKonfig,
        kalendarStyle = kalendarStyle,
        selectedDay = selectedDay,
        kalendarEvents = kalendarEvents,
        onCurrentDayClick = onCurrentDayClick,
        errorMessageLogged = errorMessage
    )
}
