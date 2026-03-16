package com.sweeteri.stepikclient

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sweeteri.stepikclient.navigation.Screen
import com.sweeteri.stepikclient.screens.login.LoginScreen
import com.sweeteri.stepikclient.screens.main.MainScreen
import com.sweeteri.stepikclient.screens.welcome.WelcomeScreen
import com.sweeteri.stepikclient.ui.theme.StepikTheme


@Composable
fun App() {
    val colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    StepikTheme {
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
                    WelcomeScreen(
                        onLoginClick = {
                            navController.navigate(Screen.Login.route)
                        },
                        onSignUpClick = {
                            /* TODO */
                        }
                    )
                }

                composable(Screen.Login.route) {
                    LoginScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onRegisterClick = {
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        },
                        onLoginSuccess = {
                            navController.navigate(Screen.Main.route) {
                                popUpTo(Screen.Login.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
                composable(Screen.Main.route) {
                    MainScreen()
                }
            }
        }
    }
}
