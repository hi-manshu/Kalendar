package com.himanshoe.sample


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.KalendarFirey
import com.himanshoe.kalendar.KalendarOceanic
import com.himanshoe.kalendar.core.color.KalendarColorScheme
import com.himanshoe.kalendar.core.config.KalendarDayKonfig
import com.himanshoe.kalendar.core.util.OnDaySelectionAction
import com.himanshoe.kalendar.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun App() {
    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        KalendarFirey(
            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            modifier = Modifier.fillMaxWidth(),
            events = KalendarEvents(),
            onDaySelectionAction = OnDaySelectionAction.Range { date, events ->
                println("Selected Date: $date with events: $events")
            },
        )
        KalendarOceanic(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            arrowShown = true,
            colorScheme = KalendarColorScheme.default(), // Provide an appropriate color scheme
            showDayLabel = true,
            dayKonfig = KalendarDayKonfig.default(), // Provide an appropriate day configuration
            events = KalendarEvents(), // Provide the events
            startDayOfWeek = DayOfWeek.SUNDAY,
            onDaySelectionAction = OnDaySelectionAction.Single{ date, events ->
                println("Selected Date: $date with events: $events")
            },
        )
    }
}