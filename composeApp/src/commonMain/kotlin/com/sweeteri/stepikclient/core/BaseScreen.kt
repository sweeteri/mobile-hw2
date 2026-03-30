package com.sweeteri.stepikclient.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sweeteri.stepikclient.presentation.common.components.FullScreenStateOverlay
import com.sweeteri.stepikclient.presentation.common.components.PaginationErrorRow
import com.sweeteri.stepikclient.presentation.common.components.PaginationLoader
import com.sweeteri.stepikclient.presentation.common.state.BaseListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BaseListScreen(
    listState: BaseListState<T>,
    lazyListState: LazyListState,
    onLoadNext: () -> Unit,
    onRefresh: (() -> Unit)? = null,
    topContent: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {

        topContent?.invoke()

        val content: @Composable () -> Unit = {
            Box(modifier = Modifier.fillMaxSize()) {

                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(listState.items) { item ->
                        itemContent(item)
                    }

                    item {
                        when {
                            listState.isLoading -> PaginationLoader()
                            listState.error != null -> PaginationErrorRow(onLoadNext)
                        }
                    }
                }

                if (listState.items.isEmpty()) {
                    FullScreenStateOverlay(
                        isLoading = listState.isLoading,
                        error = listState.error,
                        onRetry = onRefresh ?: onLoadNext
                    )
                }
            }
        }

        if (onRefresh != null) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(listState.isRefreshing),
                onRefresh = onRefresh,
                modifier = Modifier.weight(1f)
            ) {
                content()
            }
        } else {
            Box(modifier = Modifier.weight(1f)) {
                content()
            }
        }
    }
}