package com.himanshoe.kalendarkit.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class KalendarPagingSource : PagingSource<Int, Int>() {

    override fun getRefreshKey(state: PagingState<Int, Int>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = ListDataProvider.get6monthsValue(nextPageNumber)
            return LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
