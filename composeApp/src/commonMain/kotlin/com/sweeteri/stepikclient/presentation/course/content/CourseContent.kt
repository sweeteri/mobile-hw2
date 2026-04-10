package com.sweeteri.stepikclient.presentation.course.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.sweeteri.stepikclient.data.local.mapper.toCourseTab
import com.sweeteri.stepikclient.data.local.mapper.toIndex
import com.sweeteri.stepikclient.presentation.common.components.PaginationErrorRow
import com.sweeteri.stepikclient.presentation.course.components.CourseHeader
import com.sweeteri.stepikclient.presentation.course.components.CourseTabs

import com.sweeteri.stepikclient.presentation.course.state.CourseDetailIntent
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailState
import com.sweeteri.stepikclient.presentation.course.state.CourseTab
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun CourseContent(
    state: CourseDetailState,
    padding: PaddingValues,
    onIntent: (CourseDetailIntent) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState, state.selectedTab) {

        if (state.selectedTab != CourseTab.REVIEWS) return@LaunchedEffect

        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
            .distinctUntilChanged()
            .debounce(300)
            .collect { lastIndex ->

                val total = listState.layoutInfo.totalItemsCount

                if (lastIndex != null &&
                    lastIndex >= total - 3 &&
                    !state.isReviewsLoading &&
                    state.hasMore
                ) {
                    onIntent(CourseDetailIntent.LoadNextReviews)
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {

        item {
            state.course?.let {
                CourseHeader(it) {
                    onIntent(CourseDetailIntent.Enroll)
                }
            }
        }

        stickyHeader {
            CourseTabs(
                selectedTab = state.selectedTab.toIndex(),
                onTabSelected = {
                    onIntent(CourseDetailIntent.SelectTab(it.toCourseTab()))
                }
            )
        }

        when (state.selectedTab) {

            CourseTab.OVERVIEW -> overviewItems(state)

            CourseTab.REVIEWS -> reviewsItems(state, onIntent)

            CourseTab.CURRICULUM -> curriculumItems(state)
        }
        if (state.reviewsError != null) {
            item {
                PaginationErrorRow(
                    onRetry = { onIntent(CourseDetailIntent.LoadNextReviews) }
                )
            }
        }
    }
}