package com.himanshoe.kalendarsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.endlos.Kalendar
import com.himanshoe.kalendarsample.ui.theme.KalendarTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalendarTheme {
                Column {
                    com.himanshoe.kalendar.Kalendar(
                        currentDay = Clock.System.todayIn(
                            TimeZone.currentSystemDefault()
                        ),
                        kalendarType = KalendarType.Oceanic
                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    com.himanshoe.kalendar.Kalendar(
                        currentDay = Clock.System.todayIn(
                            TimeZone.currentSystemDefault()
                        ),
                        kalendarType = KalendarType.Firey
                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    Kalendar(
                        modifier = Modifier.fillMaxSize(),
                    )

                }

            }
        }
    }
}

