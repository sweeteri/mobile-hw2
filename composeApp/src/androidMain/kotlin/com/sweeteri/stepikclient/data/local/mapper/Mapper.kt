package com.sweeteri.stepikclient.data.local.mapper

import com.sweeteri.stepikclient.data.local.CourseEntity
import com.sweeteri.stepikclient.data.local.model.Course
import com.sweeteri.stepikclient.data.remote.dto.CourseDto

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