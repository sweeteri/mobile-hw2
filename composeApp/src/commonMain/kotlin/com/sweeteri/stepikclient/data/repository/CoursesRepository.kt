package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.data.local.model.Course

interface CoursesRepository {
    suspend fun getCourses(page: Int, query: String): Result<List<Course>>
}