package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.data.local.model.ReviewPage

interface CourseDetailRepository {
    suspend fun getCourseDetail(courseId: Int): Result<CourseDetail>
    suspend fun enroll(courseId: Int, userId: Int): Result<Boolean>
    suspend fun getReviews(
        courseId: Int,
        page: Int,
        score: Int?
    ): Result<ReviewPage>
}