package com.sweeteri.stepikclient.presentation.course


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    courseId: Int,
    viewModel: CourseDetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(courseId) {
        viewModel.loadCourseDetail(courseId)
    }

    Scaffold(
        topBar = { CourseTopBar(onBack) }
    ) { padding ->

        when {
            state.isLoading && state.course == null -> LoadingState(padding)
            state.error != null -> ErrorState(state.error!!, padding) {
                viewModel.loadCourseDetail(courseId)
            }

            state.course != null -> CourseContent(
                course = state.course!!,
                padding = padding,
                onEnroll = { viewModel.enroll(state.course!!.id.toInt(), 0) }
            )
        }
    }
}
