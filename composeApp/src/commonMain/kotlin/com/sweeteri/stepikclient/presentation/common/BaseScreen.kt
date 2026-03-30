package com.sweeteri.core

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BaseListScreen(
    listState: BaseListState<T>,
    lazyListState: LazyListState,
    onLoadNext: () -> Unit,
    onRefresh: (() -> Unit)? = null,
    topContent: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit,
    loaderContent: @Composable () -> Unit,
    errorContent: @Composable (onRetry: () -> Unit) -> Unit,
    emptyContent: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        // Верхний контент (например заголовок)
        topContent?.invoke()

        val content: @Composable () -> Unit = {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Основной список элементов
                    items(listState.items) { item ->
                        itemContent(item)
                    }

                    // Пагинация / ошибка
                    item {
                        when {
                            listState.isLoading -> loaderContent()
                            listState.error != null -> errorContent(onLoadNext)
                        }
                    }
                }

                // Пустое состояние
                if (listState.items.isEmpty()) {
                    emptyContent()
                }
            }
        }

        // Обёртка для pull-to-refresh, если нужно
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