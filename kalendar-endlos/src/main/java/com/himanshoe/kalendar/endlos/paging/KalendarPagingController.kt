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

package com.himanshoe.kalendar.endlos.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * Controller for managing the paging functionality of Kalendar items.
 */
@Stable
class KalendarPagingController {

    private val repository = KalendarRepository()

    /**
     * Flow of Kalendar items loaded from the repository using the Paging library.
     */
    val kalendarItems = Pager(PagingConfig(pageSize = 12)) {
        KalendarPagingSource(repository)
    }.flow
}

/**
 * Remembers an instance of [KalendarPagingController].
 *
 * @return The remembered [KalendarPagingController] instance.
 */
@Composable
fun rememberKalendarPagingController(): KalendarPagingController {
    return remember {
        KalendarPagingController()
    }
}
