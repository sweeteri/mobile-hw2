package com.sweeteri.stepikclient.presentation.navigation

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Onboarding : Screen("onboarding")
    object MainRoot : Screen("main_root")
    object Home : Screen("home")
    object Search : Screen("search")
    object Notifications : Screen("notifications")
    object Profile : Screen("profile")

    object CourseDetail {
        const val route = "course_detail"
        const val routeWithArg = "$route/{courseId}"
        fun createRoute(courseId: Int) = "$route/$courseId"
    }
}
