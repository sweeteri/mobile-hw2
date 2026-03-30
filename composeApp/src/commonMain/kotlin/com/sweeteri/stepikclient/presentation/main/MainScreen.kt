package com.sweeteri.stepikclient.presentation.main

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweeteri.stepikclient.presentation.common.BaseListScreen
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.home_title
import com.sweeteri.stepikclient.presentation.common.components.CourseCard
import com.sweeteri.stepikclient.presentation.common.components.FullScreenStateOverlay
import com.sweeteri.stepikclient.presentation.common.components.PaginationErrorRow
import com.sweeteri.stepikclient.presentation.common.components.PaginationLoader
import com.sweeteri.stepikclient.presentation.common.extensions.OnBottomReached
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

    BackHandlerWithExit()

    listState.OnBottomReached {
        viewModel.processIntent(MainIntent.LoadNextPage)
    }

    BaseListScreen(
        listState = state.listState,
        lazyListState = listState,
        onLoadNext = { viewModel.processIntent(MainIntent.LoadNextPage) },
        onRefresh = { viewModel.processIntent(MainIntent.Refresh) },
        topContent = {
            ScreenHeader(stringResource(Res.string.home_title))
        },
        itemContent = { course ->
            CourseCard(course)
        },
        loaderContent = {
            PaginationLoader()
        },
        errorContent = { onRetry ->
            PaginationErrorRow(onRetry)
        },
        emptyContent = {
            FullScreenStateOverlay(
                isLoading = state.listState.isLoading,
                error = state.listState.error,
                onRetry = { viewModel.processIntent(MainIntent.Refresh) }
            )
        }
    )
}
