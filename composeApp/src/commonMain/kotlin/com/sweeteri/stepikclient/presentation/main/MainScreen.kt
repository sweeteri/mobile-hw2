package com.sweeteri.stepikclient.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sweeteri.core.UiEvent
import com.sweeteri.stepikclient.presentation.common.BaseListScreen
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.home_title
import com.sweeteri.stepikclient.presentation.common.components.CourseCard
import com.sweeteri.stepikclient.presentation.common.components.FullScreenStateOverlay
import com.sweeteri.stepikclient.presentation.common.components.PaginationErrorRow
import com.sweeteri.stepikclient.presentation.common.components.PaginationLoader
import com.sweeteri.stepikclient.presentation.common.extensions.OnBottomReached
import com.sweeteri.stepikclient.presentation.navigation.Screen
import org.jetbrains.compose.resources.stringResource

@Composable
expect fun BackHandlerWithExit()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    BackHandlerWithExit()

    listState.OnBottomReached {
        viewModel.processIntent(MainIntent.LoadNextPage)
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
        onLoadNext = { viewModel.processIntent(MainIntent.LoadNextPage) },
        onRefresh = { viewModel.processIntent(MainIntent.Refresh) },
        topContent = {
            ScreenHeader(stringResource(Res.string.home_title))
        },
        itemContent = { course ->
            CourseCard(course, modifier = Modifier.clickable {
                val courseIdInt = course.id.toIntOrNull() ?: return@clickable
                navController.navigate(Screen.CourseDetail.createRoute(courseIdInt))
            })
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
