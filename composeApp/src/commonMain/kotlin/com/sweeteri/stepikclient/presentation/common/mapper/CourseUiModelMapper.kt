package com.sweeteri.stepikclient.presentation.common.mapper

import com.sweeteri.stepikclient.data.local.model.Course
import com.sweeteri.stepikclient.presentation.common.model.CourseUiModel
import kotlin.math.roundToInt

fun Course.toUiModel(): CourseUiModel {
    return CourseUiModel(
        id = id,
        title = title,
        author = author,
        imageUrl = imageUrl,
        average = formatAverage(average),
        learners = formatLearners(learnersCount)
    )
}

fun formatLearners(count: Int): String {
    return when {
        count >= 1000 -> {
            val thousands = count / 1000.0
            val rounded = (thousands * 10).roundToInt() / 10.0
            "${rounded}k"
        }

        else -> count.toString()
    }
}

fun formatAverage(value: Double): String {
    return if (value > 0) {
        val rounded = (value * 10).roundToInt() / 10.0
        "$rounded"
    } else {
        "—"
    }
}
