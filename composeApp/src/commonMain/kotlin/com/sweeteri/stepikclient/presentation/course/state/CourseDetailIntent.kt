package com.sweeteri.stepikclient.presentation.course.state

sealed interface CourseDetailIntent {

    data class Load(val courseId: Int) : CourseDetailIntent

    data class SelectTab(val tab: CourseTab) : CourseDetailIntent

    object LoadNextReviews : CourseDetailIntent

    data class SelectRating(val rating: Int?) : CourseDetailIntent

    object Enroll : CourseDetailIntent
}