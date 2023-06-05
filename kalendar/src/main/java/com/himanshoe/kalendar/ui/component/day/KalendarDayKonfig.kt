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

package com.himanshoe.kalendar.ui.component.day

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Configuration data class for customizing the appearance of a Kalendar day.
 *
 * @param size The size of the Kalendar day.
 * @param textSize The text size of the day.
 * @param textColor The text color of the day.
 * @param selectedTextColor The text color of the selected day.
 * @param borderColor The border color of the day.
 */
@Immutable
data class KalendarDayKonfig(
    val size: Dp,
    val textSize: TextUnit,
    val textColor: Color,
    val selectedTextColor: Color,
    val borderColor: Color = Color.Black,
) {
    companion object {

        @SuppressWarnings("MagicNumber")
        fun default() = KalendarDayKonfig(
            size = 56.dp,
            textSize = 16.sp,
            textColor = Color(0xFF413D4B),
            selectedTextColor = Color.White
        )
    }
}
