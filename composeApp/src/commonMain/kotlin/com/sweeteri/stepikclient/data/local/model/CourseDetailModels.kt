package com.sweeteri.stepikclient.data.local.model

import androidx.compose.runtime.Immutable


@Immutable
data class CourseDetail(
    val id: String,
    val title: String,
    val description: String = "",
    val imageUrl: String = "",
    val authors: List<Author> = emptyList(),
    val average: Double = 0.0,
    val reviewCount: Int = 0,
    val lessonCount: Int = 0,
    val learnersCount: Int = 0,
    val sections: List<Section> = emptyList(),
    val isEnrolled: Boolean = false
)

@Immutable
data class Author(
    val id: String,
    val fullName: String
)

@Immutable
data class Section(
    val id: String,
    val title: String,
    val units: List<UnitCourse> = emptyList()
)

@Immutable
data class UnitCourse(
    val id: String,
    val title: String,
    val lessons: List<Lesson> = emptyList()
)

@Immutable
data class Lesson(
    val id: String,
    val title: String
)