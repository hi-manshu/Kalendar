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

package com.himanshoe.kalendar.ui.firey

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

/**
 * Data class representing a selected day range in the Kalendar.
 * @param start The start date of the range.
 * @param end The end date of the range.
 */
@Immutable
data class KalendarSelectedDayRange(
    val start: LocalDate,
    val end: LocalDate
) {
    /**
     * Checks if the selected day range is empty (start date is after end date).
     * @return True if the range is empty, false otherwise.
     */
    fun isEmpty() = start > end

    /**
     * Checks if the selected day range contains a single date (start and end dates are the same).
     * @return True if the range contains a single date, false otherwise.
     */
    fun isSingleDate() = start == end
}
