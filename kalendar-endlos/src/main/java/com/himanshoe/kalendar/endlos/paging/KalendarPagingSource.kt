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
            val kalendarItems = kalendarRepository.generateDates(page)
                .filter { date ->
                    date.year >= today.year && date.month.value >= today.monthNumber
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
