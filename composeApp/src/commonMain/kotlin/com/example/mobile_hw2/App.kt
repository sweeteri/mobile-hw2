package com.example.mobile_hw2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile_hw2.screens.WelcomeScreen
import com.example.mobile_hw2.screens.LoginScreen
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.*
import androidx.compose.animation.core.tween


@Composable
@Preview
fun App() {
    val useDarkTheme = isSystemInDarkTheme()

    val colorScheme = if (useDarkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }

    MaterialTheme(colorScheme = colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "welcome",
                enterTransition = {
                    slideInHorizontally(
                        animationSpec = tween(500),
                        initialOffsetX = { it }) + fadeIn(tween(500))
                },
                exitTransition = {
                    slideOutHorizontally(
                        animationSpec = tween(500),
                        targetOffsetX = { -it }) + fadeOut(tween(500))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        animationSpec = tween(500),
                        initialOffsetX = { -it }) + fadeIn(tween(500))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        animationSpec = tween(500),
                        targetOffsetX = { it }) + fadeOut(tween(500))
                }
            ) {
                composable("welcome") {
                    WelcomeScreen(onLoginClick = { navController.navigate("login") })
                }
                composable("login") {
                    LoginScreen()
                }
            }
        }
    }
}