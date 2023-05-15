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

package com.himanshoe.kalendar.endlos.daterange

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

/**
 * Represents a selected day range in a calendar.
 *
 * @property start The start date of the selected range.
 * @property end The end date of the selected range.
 */
@Immutable
data class KalendarSelectedDayRange(
    val start: LocalDate,
    val end: LocalDate
) {
    /**
     * Checks if the selected day range is empty.
     * Returns true if the start date is after the end date, indicating an empty range.
     *
     * @return true if the range is empty, false otherwise.
     */
    fun isEmpty(): Boolean {
        return start > end
    }

    /**
     * Checks if the selected day range contains a single date.
     * Returns true if the start date is the same as the end date, indicating a single date range.
     *
     * @return true if the range is a single date, false otherwise.
     */
    fun isSingleDate(): Boolean {
        return start == end
    }
}
