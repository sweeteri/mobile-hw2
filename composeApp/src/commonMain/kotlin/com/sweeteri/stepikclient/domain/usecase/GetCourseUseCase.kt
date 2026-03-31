package com.sweeteri.stepikclient.domain.usecase

import com.sweeteri.stepikclient.data.repository.CourseDetailRepository

class GetCourseDetailUseCase(private val repository: CourseDetailRepository) {
    suspend operator fun invoke(courseId: Int) = repository.getCourseDetail(courseId)
}

class EnrollCourseUseCase(private val repository: CourseDetailRepository) {
    suspend operator fun invoke(courseId: Int, userId: Int) = repository.enroll(courseId, userId)
}