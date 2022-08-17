package com.himanshoe.kalendarkit

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate


internal class KalendarViewModel : ViewModel() {

    fun getCalendarData(startMonth: Int = 0): Flow<PagingData<LocalDate>> =
        Pager(config = PagingConfig(
            12,
            enablePlaceholders = true,
            initialLoadSize = 12,
            prefetchDistance = 5
        ), pagingSourceFactory = {
            KalendarPagingSource().apply { changeStartingMonth(startMonth) }
        }).flow

}
