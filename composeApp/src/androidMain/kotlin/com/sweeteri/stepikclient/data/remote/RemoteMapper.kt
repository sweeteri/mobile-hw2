package com.sweeteri.stepikclient.data.remote

import com.sweeteri.stepikclient.data.local.model.Course
import com.sweeteri.stepikclient.data.remote.dto.CourseDto

fun CourseDto.toDomain() = Course(
    id = id.toString(),
    title = title,
    author = summary.orEmpty(),
    imageUrl = cover.orEmpty(),
    average = average,
    learnersCount = learnersCount ?: 0
)