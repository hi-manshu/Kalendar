/*
 * Copyright 2022 Kalendar Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.himanshoe.kalendar.endlos.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.endlos.component.header.config.KalendarHeaderConfig
import com.himanshoe.kalendar.endlos.component.text.KalendarTitle
import kotlinx.datetime.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun KalendarHeader(
    modifier: Modifier = Modifier,
    month: Month,
    year: Int,
    kalendarHeaderConfig: KalendarHeaderConfig,

) {
    KalendarTitle(
        modifier = modifier.fillMaxWidth(),
        text = getTitleText(month, year),
        kalendarTextConfig = kalendarHeaderConfig.kalendarTextConfig
    )
}

internal fun getTitleText(month: Month, year: Int): String {
    return month.getDisplayName(TextStyle.FULL, Locale.getDefault()).lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } + " " + year
}
