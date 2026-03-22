package com.sweeteri.stepikclient.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.home_title
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource


@Composable
expect fun BackHandlerWithExit()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onProfileClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    BackHandlerWithExit()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
            .distinctUntilChanged()
            .collect { lastVisibleItem ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (totalItems > 0 && lastVisibleItem >= totalItems - 5) {
                    viewModel.processIntent(MainIntent.LoadNextPage)
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScreenHeader(stringResource(Res.string.home_title), onProfileClick = onProfileClick)

        SearchField(
            query = state.searchQuery,
            onQueryChange = {
                viewModel.processIntent(MainIntent.SearchChanged(it))
            }
        )
        SwipeRefresh(
            state = rememberSwipeRefreshState(state.isRefreshing),
            onRefresh = { viewModel.processIntent(MainIntent.Refresh) },
            modifier = Modifier.weight(1f)
        ) {

            Box(modifier = Modifier.weight(1f)) {
                CoursesList(
                    state = state,
                    listState = listState,
                    onRetryPagination = { viewModel.processIntent(MainIntent.LoadNextPage) }
                )

                if (state.courses.isEmpty()) {
                    when {
                        state.isLoading && !state.isRefreshing -> {
                            FullScreenStateOverlay(
                                isLoading = true,
                                error = null,
                                onRetry = {}
                            )
                        }

                        state.error != null -> {
                            FullScreenStateOverlay(
                                isLoading = false,
                                error = state.error,
                                onRetry = { viewModel.processIntent(MainIntent.Refresh) }
                            )
                        }

                        !state.isRefreshing -> {
                            FullScreenStateOverlay(
                                isLoading = false,
                                error = null,
                                onRetry = {}
                            )
                        }
                    }
                }
            }
        }
    }
}
