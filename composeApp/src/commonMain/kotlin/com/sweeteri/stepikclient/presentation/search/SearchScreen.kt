package com.sweeteri.stepikclient.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sweeteri.core.UiEvent
import com.sweeteri.stepikclient.presentation.common.BaseListScreen
import com.sweeteri.stepikclient.presentation.common.CourseCardSkeleton
import com.sweeteri.stepikclient.presentation.common.components.CourseCard
import com.sweeteri.stepikclient.presentation.common.components.FullScreenStateOverlay
import com.sweeteri.stepikclient.presentation.common.components.PaginationErrorRow
import com.sweeteri.stepikclient.presentation.common.components.PaginationLoader
import com.sweeteri.stepikclient.presentation.common.extensions.OnBottomReached
import com.sweeteri.stepikclient.presentation.navigation.Screen
import com.sweeteri.stepikclient.presentation.search.components.SearchField

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    listState.OnBottomReached {
        viewModel.processIntent(SearchIntent.LoadNextPage)
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UiEvent.OpenCourseDetail -> {
                    val courseIdInt = event.courseId.toIntOrNull() ?: return@collect
                    navController.navigate(Screen.CourseDetail.createRoute(courseIdInt))
                }
            }
        }
    }
    BaseListScreen(
        listState = state.listState,
        lazyListState = listState,
        onLoadNext = { viewModel.processIntent(SearchIntent.LoadNextPage) },
        onRefresh = { viewModel.processIntent(SearchIntent.Refresh) },
        topContent = {
            SearchField(
                query = state.query,
                onQueryChange = {
                    viewModel.processIntent(SearchIntent.QueryChanged(it))
                }
            )
        },

        itemContent = { course ->
            CourseCard(course, modifier = Modifier.clickable {
                val courseIdInt = course.id.toIntOrNull() ?: return@clickable
                navController.navigate(Screen.CourseDetail.createRoute(courseIdInt))
            })
        },
        loaderContent = { CourseCardSkeleton() },
        errorContent = { onRetry -> PaginationErrorRow(onRetry) },
        emptyContent = { FullScreenStateOverlay(
            isLoading = state.listState.isLoading,
            error = state.listState.error,
            onRetry = { viewModel.processIntent(SearchIntent.Refresh) }
        ) }
    )
}
