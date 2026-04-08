package com.sweeteri.stepikclient.presentation.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.domain.usecase.EnrollCourseUseCase
import com.sweeteri.stepikclient.domain.usecase.GetCourseDetailUseCase
import com.sweeteri.stepikclient.domain.usecase.GetCourseReviewsUseCase
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailIntent
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDetailViewModel(
    private val getCourseDetail: GetCourseDetailUseCase,
    private val enrollCourse: EnrollCourseUseCase,
    private val getCourseReviews: GetCourseReviewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CourseDetailState(isLoading = true))
    val state: StateFlow<CourseDetailState> = _state

    private var currentCourseId: Int = 0

    fun process(intent: CourseDetailIntent) {
        when (intent) {

            is CourseDetailIntent.Load -> loadCourse(intent.courseId)

            is CourseDetailIntent.SelectTab -> {
                _state.update { it.copy(selectedTab = intent.tab) }
            }

            CourseDetailIntent.LoadNextReviews -> loadNextReviews()

            is CourseDetailIntent.SelectRating -> {
                _state.update {
                    it.copy(
                        selectedRating = intent.rating,
                        currentPage = 1,
                        reviews = emptyList(),
                        hasMore = true
                    )
                }
                loadReviews(1)
            }

            CourseDetailIntent.Enroll -> enroll()
        }
    }

    private fun loadCourse(courseId: Int) {
        currentCourseId = courseId

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            getCourseDetail(courseId)
                .onSuccess { course ->

                    _state.update {
                        it.copy(
                            isLoading = false,
                            course = course
                        )
                    }

                    loadReviews(1)
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "Unknown error"
                        )
                    }
                }
        }
    }

    private fun loadReviews(page: Int) {
        val state = _state.value

        if (state.isReviewsLoading || !state.hasMore) return

        viewModelScope.launch {

            _state.update { it.copy(isReviewsLoading = true) }

            val result = getCourseReviews(currentCourseId, page)

            result.onSuccess { pageData ->

                _state.update { current ->

                    val newList = if (page == 1) {
                        pageData.reviews
                    } else {
                        current.reviews + pageData.reviews
                    }

                    current.copy(
                        reviews = newList,
                        isReviewsLoading = false,
                        currentPage = page,
                        hasMore = pageData.reviews.isNotEmpty(),
                        reviewsError = null
                    )

                }
            }

            result.onFailure { throwable ->
                _state.update {
                    it.copy(
                        isReviewsLoading = false,
                        reviewsError = throwable.message
                    )

                }
            }
        }
    }

    private fun loadNextReviews() {
        val state = _state.value
        loadReviews(state.currentPage + 1)
    }

    private fun enroll() {
        val courseId = _state.value.course?.id ?: return

        viewModelScope.launch {
            enrollCourse(courseId.toInt(), 0)
        }
    }
}
