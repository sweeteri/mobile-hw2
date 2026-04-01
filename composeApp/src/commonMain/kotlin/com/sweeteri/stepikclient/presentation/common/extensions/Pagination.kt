package com.sweeteri.stepikclient.presentation.common.extensions

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 5,
    onLoadMore: () -> Unit
) {
    LaunchedEffect(this) {
        snapshotFlow {
            layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        }
            .distinctUntilChanged()
            .collect { lastVisibleItem ->
                val totalItems = layoutInfo.totalItemsCount
                if (totalItems > 0 && lastVisibleItem >= totalItems - buffer) {
                    onLoadMore()
                }
            }
    }
}