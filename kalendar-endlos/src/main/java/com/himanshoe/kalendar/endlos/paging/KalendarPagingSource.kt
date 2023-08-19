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

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.io.IOException

/**
 * Paging source for loading KalendarModelEntity items from the KalendarRepository.
 *
 * @param kalendarRepository The repository for retrieving KalendarModelEntity items.
 */
class KalendarPagingSource(
    private val kalendarRepository: KalendarRepository
) : PagingSource<Int, KalendarModelEntity>() {

    private val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

    /**
     * Returns the refresh key for the paging state.
     */
    override fun getRefreshKey(state: PagingState<Int, KalendarModelEntity>) = null

    /**
     * Loads the [KalendarModelEntity] items based on the provided load parameters.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, KalendarModelEntity> {
        return try {
            val page = params.key ?: today.year

            // Filter out the months before the current month and year
            val kalendarItems = kalendarRepository.generateDates(page).filter { date ->
                if (date.year == today.year) {
                    date.month.value >= today.monthNumber
                } else {
                    date.year > today.year
                }
    }


            val nextPage = page.plus(1)

            LoadResult.Page(
                data = kalendarItems,
                prevKey = null,
                nextKey = nextPage,
            )
        } catch (e: IOException) {
            LoadResult.Error(e.fillInStackTrace())
        }
    }
}
