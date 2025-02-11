package com.himanshoe.sample


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.KalendarFirey
import com.himanshoe.kalendar.KalendarOceanic
import com.himanshoe.kalendar.core.color.KalendarColorScheme
import com.himanshoe.kalendar.core.config.KalendarDayKonfig
import com.himanshoe.kalendar.core.util.DaySelectionMode
import com.himanshoe.kalendar.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun App() {
//    KalendarFirey(
//        date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//        modifier = Modifier.fillMaxWidth(),
//        events = KalendarEvents(),
//        daySelectionMode = DaySelectionMode.Range,
//        onRangeSelected = { kalendarSelectedDayRange, list ->
//            println("Selected Range: $kalendarSelectedDayRange")
//        }
//    )
    KalendarOceanic(
        selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
        arrowShown = true,
        colorScheme = KalendarColorScheme.default(), // Provide an appropriate color scheme
        showDayLabel = true,
        dayKonfig = KalendarDayKonfig.default(), // Provide an appropriate day configuration
        events = KalendarEvents(), // Provide the events
        modifier = Modifier.fillMaxWidth(),
        startDayOfWeek = DayOfWeek.SUNDAY,
        onDayClick = { date, events ->
            println("Selected Date: $date with events: $events")
        },
        onRangeSelected = { range, events ->
            println("Selected Range: $range with events: $events")
        }
    )
}