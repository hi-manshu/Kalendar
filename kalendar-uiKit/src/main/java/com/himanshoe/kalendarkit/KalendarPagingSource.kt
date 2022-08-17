package com.himanshoe.kalendarkit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.datetime.*

class KalendarPagingSource : PagingSource<Int, LocalDate>() {
    private var startingMonthFromCurrentMonth: Int = 60

    fun changeStartingMonth(startingMonthFromCurrentMonth: Int) {
        this.startingMonthFromCurrentMonth = startingMonthFromCurrentMonth
    }

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
            prevKey = if (position == (startingMonthFromCurrentMonth - (startingMonthFromCurrentMonth * 2))) null else position - 1,
            nextKey = position + 1
        )
    }
}
