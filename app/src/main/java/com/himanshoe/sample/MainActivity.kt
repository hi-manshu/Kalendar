package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(
                kalendarKonfig = KalendarKonfig(),
                kalendarStyle = KalendarStyle(
                    kalendarSelector = KalendarSelector.Circle()
                ),
                onCurrentDayClick = { date, event ->

                },
                errorMessage = {}, kalendarType = KalendarType.Firey())
        }
    }
}
