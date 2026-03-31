package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.data.local.model.CourseDetail

interface CourseDetailRepository {
    suspend fun getCourseDetail(courseId: Int): Result<CourseDetail>
    suspend fun enroll(courseId: Int, userId: Int): Result<Boolean>
}