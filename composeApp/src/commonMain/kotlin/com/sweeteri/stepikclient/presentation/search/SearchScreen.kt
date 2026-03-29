package com.sweeteri.stepikclient.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweeteri.stepikclient.presentation.main.CoursesList
import com.sweeteri.stepikclient.presentation.main.FullScreenStateOverlay
import com.sweeteri.stepikclient.presentation.search.components.SearchField
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
            .distinctUntilChanged()
            .collect { lastVisibleItem ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (totalItems > 0 && lastVisibleItem >= totalItems - 5) {
                    viewModel.processIntent(SearchIntent.LoadNextPage)
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        SearchField(
            query = state.query,
            onQueryChange = {
                viewModel.processIntent(SearchIntent.QueryChanged(it))
            }
        )

        Box(modifier = Modifier.weight(1f)) {

            CoursesList(
                items = state.listState.items,
                isLoading = state.listState.isLoading,
                error = state.listState.error,
                listState = listState,
                onRetryPagination = {
                    viewModel.processIntent(SearchIntent.LoadNextPage)
                }
            )

            if (state.listState.items.isEmpty()) {
                when {
                    state.listState.isLoading -> {
                        FullScreenStateOverlay(
                            isLoading = true,
                            error = null,
                            onRetry = {}
                        )
                    }

                    state.listState.error != null -> {
                        FullScreenStateOverlay(
                            isLoading = false,
                            error = state.listState.error,
                            onRetry = {
                                viewModel.processIntent(SearchIntent.LoadNextPage)
                            }
                        )
                    }
                }
            }
        }
    }
}