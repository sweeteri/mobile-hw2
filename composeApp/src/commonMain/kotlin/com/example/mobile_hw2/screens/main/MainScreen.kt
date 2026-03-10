package com.example.mobile_hw2.screens.main

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.home
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource


@Composable
expect fun BackHandlerWithExit()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    BackHandlerWithExit()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
            .distinctUntilChanged()
            .collect { lastVisibleItem ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (totalItems > 0 && lastVisibleItem >= totalItems - 5) {
                    viewModel.loadNextPage()
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScreenHeader(stringResource(Res.string.home))

        SearchField(
            query = state.searchQuery,
            onQueryChange = viewModel::onSearchQueryChange
        )

        Box(modifier = Modifier.weight(1f)) {
            CoursesList(
                state = state,
                listState = listState,
                onRefresh = { viewModel.refresh() },
                onRetryPagination = { viewModel.loadNextPage() }
            )

            if (state.courses.isEmpty()) {
                FullScreenStateOverlay(
                    isLoading = state.isLoading,
                    error = state.error,
                    onRetry = { viewModel.refresh() }
                )
            }
        }
    }
}