package com.sweeteri.stepikclient.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Main : Screen("main")
    object Onboarding : Screen("onboarding")
    object Profile : Screen("profile")
}
