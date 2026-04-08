package com.sweeteri.stepikclient.presentation.course.state

import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.data.local.model.CourseReview

data class CourseDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val course: CourseDetail? = null,

    val selectedTab: CourseTab = CourseTab.OVERVIEW,

    val reviews: List<CourseReview> = emptyList(),
    val isReviewsLoading: Boolean = false,

    val selectedRating: Int? = null,

    val currentPage: Int = 1,
    val hasMore: Boolean = true,
    val reviewsError: String? = null
)