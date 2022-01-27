package com.himanshoe.kalendar.endlos.util

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
internal fun InfiniteLoadingList(
    modifier: Modifier,
    items: List<Any>,
    loadMore: () -> Unit,
    rowContent: @Composable (Int, Any) -> Unit,
) {
    val listState = rememberLazyListState()
    val firstVisibleIndex = remember { mutableStateOf(listState.firstVisibleItemIndex) }
    LazyColumn(state = listState, modifier = modifier) {
        itemsIndexed(items) { index, item ->
            rowContent(index, item)
        }
    }
    if (listState.isAtLastPosition(firstVisibleIndex)) {
        loadMore()
    }
}

internal fun LazyListState.isAtLastPosition(rememberedIndex: MutableState<Int>): Boolean {
    val firstVisibleIndex = this.firstVisibleItemIndex
    if (rememberedIndex.value != firstVisibleIndex) {
        rememberedIndex.value = firstVisibleIndex
        return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
    }
    return false
}
