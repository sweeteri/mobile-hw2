package com.sweeteri.stepikclient.data.local.mapper

import com.sweeteri.stepikclient.presentation.course.state.CourseTab

fun List<Int>.toDistributionMap(): Map<Int, Int> {
    return mapOf(
        1 to getOrElse(0) { 0 },
        2 to getOrElse(1) { 0 },
        3 to getOrElse(2) { 0 },
        4 to getOrElse(3) { 0 },
        5 to getOrElse(4) { 0 }
    )
}
fun mapLanguage(code: String): String {
    return when (code) {
        "ru" -> "Russian"
        "en" -> "English"
        else -> code
    }
}
fun CourseTab.toIndex(): Int = when (this) {
    CourseTab.OVERVIEW -> 0
    CourseTab.REVIEWS -> 1
    CourseTab.CURRICULUM -> 2
}

fun Int.toCourseTab(): CourseTab = when (this) {
    0 -> CourseTab.OVERVIEW
    1 -> CourseTab.REVIEWS
    2 -> CourseTab.CURRICULUM
    else -> CourseTab.OVERVIEW
}