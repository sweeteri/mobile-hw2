package com.example.mobile_hw2

import com.example.mobile_hw2.navigation.Screen
import com.example.mobile_hw2.screens.welcome.WelcomeScreen
import com.example.mobile_hw2.screens.login.LoginScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
@Preview
fun App() {
    val colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.Welcome.route,

                enterTransition = { slideInHorizontally(tween(500), { it }) + fadeIn(tween(500)) },
                exitTransition = {
                    slideOutHorizontally(
                        tween(500),
                        { -it }) + fadeOut(tween(500))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        tween(500),
                        { -it }) + fadeIn(tween(500))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        tween(500),
                        { it }) + fadeOut(tween(500))
                }
            ) {
                composable(Screen.Welcome.route) {
                    WelcomeScreen(onLoginClick = {
                        navController.navigate(Screen.Login.route)
                    })
                }
                composable(Screen.Login.route) {
                    LoginScreen(
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
