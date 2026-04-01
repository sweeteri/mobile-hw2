package com.sweeteri.stepikclient.domain.usecase
import com.sweeteri.stepikclient.data.repository.CoursesRepository


class GetCoursesUseCase(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(page: Int, query: String) =
        repository.getCourses(page, query)
}