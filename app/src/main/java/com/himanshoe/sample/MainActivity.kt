package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Kalendar(
                    modifier = Modifier.fillMaxSize(),
                    kalendarType = KalendarType.Firey,
                    onCurrentDayClick = { kalendarDay, event ->
                    }
                )
            }
        }
    }
}
