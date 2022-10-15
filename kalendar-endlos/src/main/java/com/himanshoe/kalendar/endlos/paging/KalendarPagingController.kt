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
package com.himanshoe.kalendar.endlos.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

class KalendarPagingController {

    val dates: Flow<PagingData<LocalDate>> = Pager(
        config = PagingConfig(
            12, enablePlaceholders = true, initialLoadSize = 12, prefetchDistance = 5
        ),
        pagingSourceFactory = { KalendarPagingSource() }
    ).flow
}

@Composable
fun rememberKalendarPagingController(): KalendarPagingController {
    return remember {
        KalendarPagingController()
    }
}
