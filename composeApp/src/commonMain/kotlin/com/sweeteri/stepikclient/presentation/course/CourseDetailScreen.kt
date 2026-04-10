package com.sweeteri.stepikclient.presentation.course


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.home_error_button
import com.sweeteri.stepikclient.generated.resources.home_error_title
import com.sweeteri.stepikclient.presentation.common.components.ErrorView
import com.sweeteri.stepikclient.presentation.common.components.PaginationLoader
import com.sweeteri.stepikclient.presentation.course.components.CourseTopBar
import com.sweeteri.stepikclient.presentation.course.content.CourseContent
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailIntent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CourseDetailScreen(
    courseId: Int,
    viewModel: CourseDetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(courseId) {
        viewModel.process(CourseDetailIntent.Load(courseId))
    }

    Scaffold(
        topBar = { CourseTopBar(onBack) }
    ) { padding ->

        when {
            state.isLoading -> PaginationLoader()

            state.error != null -> ErrorView(
                title = stringResource(Res.string.home_error_title),
                message = state.error ?: "",
                retryMessage = stringResource(Res.string.home_error_button),
                onRetry = {
                    viewModel.process(CourseDetailIntent.Load(courseId))
                }
            )

            else -> CourseContent(
                state = state,
                padding = padding,
                onIntent = viewModel::process
            )
        }
    }
}
