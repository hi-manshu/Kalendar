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

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

internal class KalendarPagingSource : PagingSource<Int, LocalDate>() {

    override fun getRefreshKey(state: PagingState<Int, LocalDate>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocalDate> {
        val position = params.key ?: 0
        val calendarDateList = mutableListOf<LocalDate>()
        val currentDate = Clock.System
            .todayIn(TimeZone.currentSystemDefault())
            .plus(position.toLong(), DateTimeUnit.MONTH)

        val currentMonth = currentDate.month
        val currentYear = currentDate.year
        val monthStartDate = LocalDate(currentYear, currentMonth, 1)

        calendarDateList.add(monthStartDate)
        return LoadResult.Page(
            data = calendarDateList,
            prevKey = null,
            nextKey = position.plus(1)
        )
    }
}
