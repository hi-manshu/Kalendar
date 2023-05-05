package com.himanshoe.kalendarsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.ui.component.header.KalendarTextConfig
import com.himanshoe.kalendar.ui.firey.KalendarFirey
import com.himanshoe.kalendar.ui.firey.KalendarFireyPreview
import com.himanshoe.kalendar.ui.oceanic.KalendarOceanic
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
                    KalendarOceanic(
                        currentDay = Clock.System.todayIn(
                            TimeZone.currentSystemDefault()
                        ),
                    )
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                    KalendarFirey(
                        currentDay = Clock.System.todayIn(
                            TimeZone.currentSystemDefault()
                        ),
                    )
                }
            }
        }
    }
}

