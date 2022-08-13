package com.himanshoe.kalendarkit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.himanshoe.kalendarkit.data.KalendarPagingSource

class KalendarViewModel : ViewModel() {

    val kalendarPagingSource = Pager(
        PagingConfig(pageSize = 20)
    ) {
        KalendarPagingSource()
    }.flow.cachedIn(viewModelScope)
}
