package com.himanshoe.sample


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Kalendar(
            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            modifier = Modifier.fillMaxWidth(),
            events = KalendarEvents(),
            startDayOfWeek = DayOfWeek.MONDAY,
            kalendarType = KalendarType.Firey,
            onDaySelectionAction = OnDaySelectionAction.Range { date, events ->
                println("Selected Date: $date with events: $events")
            },
        )
//        Kalendar(
//            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            arrowShown = true,
//            showDayLabel = true,
//            kalendarType = KalendarType.Oceanic,
//            kalendarKonfig = KalendarKonfig(KalendarDayKonfig.default()),
//            events = KalendarEvents(), // Provide an appropriate day configuration
//            modifier = Modifier.fillMaxWidth().padding(top = 16.dp), // Provide the events
//            startDayOfWeek = DayOfWeek.SUNDAY,
//            onDaySelectionAction = OnDaySelectionAction.Range { date, events ->
//                println("Selected Date: $date with events: $events")
//            },
//            restrictToCurrentWeekOrMonth = true,
//        )
    }
}