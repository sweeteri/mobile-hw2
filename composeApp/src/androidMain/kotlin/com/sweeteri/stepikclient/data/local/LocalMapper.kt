package com.sweeteri.stepikclient.data.local

import com.sweeteri.stepikclient.data.model.Course

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