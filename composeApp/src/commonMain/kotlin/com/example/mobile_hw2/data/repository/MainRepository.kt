package com.example.mobile_hw2.data.repository

import com.example.mobile_hw2.data.model.Course

class CoursesRepository {

    fun getCourses(): List<Course> {
        return listOf(
            Course(
                id = "1",
                title = "Kotlin for Beginners",
                author = "Stepik",
                imageUrl = "https://stepik.org/static/frontend/stepik_logo.png"
            ),
            Course(
                id = "2",
                title = "Android Development",
                author = "Google",
                imageUrl = "https://stepik.org/static/frontend/stepik_logo.png"
            ),
            Course(
                id = "3",
                title = "Algorithms",
                author = "Computer Science",
                imageUrl = "https://stepik.org/static/frontend/stepik_logo.png"
            )
        )
    }
}