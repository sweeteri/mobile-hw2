package com.sweeteri.stepikclient.presentation.search

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweeteri.stepikclient.core.BaseListScreen
import com.sweeteri.stepikclient.presentation.common.components.CourseCard
import com.sweeteri.stepikclient.presentation.common.extensions.OnBottomReached
import com.sweeteri.stepikclient.presentation.search.components.SearchField
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    listState.OnBottomReached {
        viewModel.processIntent(SearchIntent.LoadNextPage)
    }

    BaseListScreen(
        listState = state.listState,
        lazyListState = listState,
        onLoadNext = {
            viewModel.processIntent(SearchIntent.LoadNextPage)
        },
        topContent = {
            SearchField(
                query = state.query,
                onQueryChange = {
                    viewModel.processIntent(SearchIntent.QueryChanged(it))
                }
            )
        }
    ) { course ->
        CourseCard(course)
    }
}
