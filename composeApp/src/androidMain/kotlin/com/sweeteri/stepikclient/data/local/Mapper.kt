package com.sweeteri.stepikclient.data.local

import com.sweeteri.stepikclient.data.model.Course
import com.sweeteri.stepikclient.data.model.CourseDto

fun CourseEntity.toDomain() = Course(
    id = id,
    title = title,
    author = author,
    imageUrl = imageUrl,
    average = average,
    learnersCount = learnersCount
)

fun Course.toEntity() = CourseEntity(
    id = id,
    title = title,
    author = author,
    imageUrl = imageUrl,
    average = average,
    learnersCount = learnersCount
)

fun CourseDto.toDomain() = Course(
    id = id.toString(),
    title = title,
    author = summary ?: "Stepik",
    imageUrl = cover ?: "",
    average = average,
    learnersCount = learnersCount ?: 0
)