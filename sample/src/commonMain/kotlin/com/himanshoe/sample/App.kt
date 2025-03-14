/*
 *
 *  * Copyright 2025 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package com.himanshoe.sample


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.foundation.action.OnDaySelectionAction
import com.himanshoe.kalendar.foundation.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun App() {
    Column(modifier = Modifier.wrapContentSize().background(Color.Blue)) {
//        Kalendar(
//            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            modifier = Modifier.fillMaxWidth(),
//            events = KalendarEvents(),
//            startDayOfWeek = DayOfWeek.SUNDAY,
//            kalendarType = KalendarType.Solaris,
//            onDaySelectionAction = OnDaySelectionAction.Range { date, events ->
//                println("Selected Date: $date with events: $events")
//            },
//        )
//        Spacer(Modifier.height(16.dp))
//        Kalendar(
//            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            modifier = Modifier.fillMaxWidth(),
//            events = KalendarEvents(),
//            startDayOfWeek = DayOfWeek.MONDAY,
//            kalendarType = KalendarType.Firey,
//            onDaySelectionAction = OnDaySelectionAction.Range { date, events ->
//                println("Selected Date: $date with events: $events")
//            },
//        )
//        Spacer(Modifier.height(16.dp))
//        Kalendar(
//            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            modifier = Modifier.fillMaxWidth(),
//            events = KalendarEvents(),
//            startDayOfWeek = DayOfWeek.MONDAY,
//            kalendarType = KalendarType.Aerial,
//            onDaySelectionAction = OnDaySelectionAction.Range { date, events ->
//                println("Selected Date: $date with events: $events")
//            },
//        )
//        Spacer(Modifier.height(16.dp))
//
        Kalendar(
            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            modifier = Modifier.fillMaxWidth(),
            events = KalendarEvents(),
            startDayOfWeek = DayOfWeek.MONDAY,
            kalendarType = KalendarType.Aerial,
            onDaySelectionAction = OnDaySelectionAction.Range { date, events ->
                println("Selected Date: $date with events: $events")
            },
        )
    }
}
