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

package com.himanshoe.kalendar.endlos

/**
 * Represents the selection mode for days in the Kalendar.
 */
sealed interface DaySelectionMode {
    object Single : DaySelectionMode // Allows selection of a single day.
    object Range : DaySelectionMode // Allows selection of a range of days.
}

/**
 * Represents the error types that can occur during range selection in the Kalendar.
 */
sealed interface RangeSelectionError {
    object EndIsBeforeStart : RangeSelectionError // Indicates that the selected end date is before the start date.
}
