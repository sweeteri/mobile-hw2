package com.sweeteri.stepikclient.presentation.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.data.repository.CourseDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CourseDetailViewModel(
    private val repository: CourseDetailRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CourseDetailState())
    val state: StateFlow<CourseDetailState> = _state

    fun loadCourseDetail(courseId: Int) {
        viewModelScope.launch {
            _state.value = CourseDetailState(isLoading = true)
            repository.getCourseDetail(courseId)
                .onSuccess { course ->
                    _state.value = CourseDetailState(course = course)
                }
                .onFailure { error ->
                    _state.value = CourseDetailState(error = error.message)
                }
        }
    }

    fun enroll(courseId: Int, userId: Int) {
        viewModelScope.launch {
            repository.enroll(courseId, userId)
                .onSuccess { enrolled ->
                    _state.value = _state.value.copy(isEnrolled = enrolled)
                }
        }
    }
}

data class CourseDetailState(
    val course: CourseDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isEnrolled: Boolean = false
)