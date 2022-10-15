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
package com.himanshoe.kalendar.endlos.component.text.config

data class KalendarTextConfig(
    val kalendarTextColor: KalendarTextColor = KalendarTextColorDefaults.kalendarTitleTextColor(),
    val kalendarTextSize: KalendarTextSize = KalendarTextSize.Title
)

internal object KalendarTextDefaults {

    fun kalendarTitleTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarTitleTextColor(),
            kalendarTextSize = KalendarTextSize.Title
        )

    fun kalendarSubTitleTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarTitleTextColor(),
            kalendarTextSize = KalendarTextSize.SubTitle
        )

    fun kalendarNormalTextConfig() =
        KalendarTextConfig(
            kalendarTextColor = KalendarTextColorDefaults.kalendarNormalTextColor(),
            kalendarTextSize = KalendarTextSize.Normal
        )
}
