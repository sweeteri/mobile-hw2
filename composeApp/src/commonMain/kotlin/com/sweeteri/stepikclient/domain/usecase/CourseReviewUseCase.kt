package com.sweeteri.stepikclient.domain.usecase

import com.sweeteri.stepikclient.data.local.model.ReviewPage
import com.sweeteri.stepikclient.data.repository.CourseDetailRepository

class GetCourseReviewsUseCase(
    private val repository: CourseDetailRepository
) {
    suspend operator fun invoke(
        courseId: Int,
        page: Int = 1,
        score: Int? = null
    ): Result<ReviewPage> {
        return repository.getReviews(courseId, page, score)
    }
}