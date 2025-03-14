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
