/*
 * Copyright 2023 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.himanshoe.kalendarsample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendarsample.ui.theme.KalendarTheme
import kotlinx.datetime.toKotlinLocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalendarTheme {
                com.himanshoe.kalendar.Kalendar(
                    currentDay = java.time.LocalDate.now().toKotlinLocalDate(),
                    kalendarType = com.himanshoe.kalendar.KalendarType.Firey,
                    kalendarColors = KalendarColors(
                        color = listOf(
                            KalendarColor(
                                backgroundColor = Color.Black,
                                dayBackgroundColor = Color.Blue,
                                headerTextColor = Color.Red
                            )
                        )
                    )
                )
            }
        }
    }
}

