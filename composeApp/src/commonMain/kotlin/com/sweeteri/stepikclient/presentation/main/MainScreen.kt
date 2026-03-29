package com.sweeteri.stepikclient.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
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
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val systemBars = WindowInsets.systemBars.asPaddingValues()
    println("DEBUG MainScreen systemBars top: ${systemBars.calculateTopPadding()} dp")
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
        ScreenHeader(stringResource(Res.string.home_title))


        SwipeRefresh(
            state = rememberSwipeRefreshState(state.listState.isRefreshing),
            onRefresh = { viewModel.processIntent(MainIntent.Refresh) },
            modifier = Modifier.weight(1f)
        ) {

            Box(modifier = Modifier.weight(1f)) {
                CoursesList(
                    items = state.listState.items,
                    isLoading = state.listState.isLoading,
                    error = state.listState.error,
                    listState = listState,
                    onRetryPagination = { viewModel.processIntent(MainIntent.LoadNextPage) }
                )

                if (state.listState.items.isEmpty()) {
                    when {
                        state.listState.isLoading && !state.listState.isRefreshing -> {
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
                                onRetry = { viewModel.processIntent(MainIntent.Refresh) }
                            )
                        }

                        !state.listState.isRefreshing -> {
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
