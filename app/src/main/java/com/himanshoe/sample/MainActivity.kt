package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(kalendarType = KalendarType.Oceanic(),
                onCurrentDayClick = {},
                errorMessage = {})
        }
    }
}
