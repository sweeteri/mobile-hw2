package com.sweeteri.stepikclient

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sweeteri.stepikclient.navigation.Screen
import com.sweeteri.stepikclient.screens.login.LoginScreen
import com.sweeteri.stepikclient.screens.main.MainScreen
import com.sweeteri.stepikclient.screens.onboarding.OnboardingScreen
import com.sweeteri.stepikclient.screens.onboarding.StartViewModel
import com.sweeteri.stepikclient.screens.welcome.WelcomeScreen
import com.sweeteri.stepikclient.ui.theme.StepikTheme
import kotlinx.coroutines.launch


@Composable
fun App() {
    StepikTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            val navController = rememberNavController()

            val prefs = remember { createAppPreferences() }

            val scope = rememberCoroutineScope()

            val startViewModel = remember {
                StartViewModel(prefs)
            }

            val startDestination by startViewModel.startDestination.collectAsState()

            if (startDestination == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                return@Surface
            }

            NavHost(
                navController = navController,
                startDestination = startDestination!!,

                enterTransition = {
                    slideInHorizontally(tween(500)) { it } + fadeIn(tween(500))
                },
                exitTransition = {
                    slideOutHorizontally(tween(500)) { -it } + fadeOut(tween(500))
                },
                popEnterTransition = {
                    slideInHorizontally(tween(500)) { -it } + fadeIn(tween(500))
                },
                popExitTransition = {
                    slideOutHorizontally(tween(500)) { it } + fadeOut(tween(500))
                }
            ) {

                composable(Screen.Onboarding.route) {
                    OnboardingScreen(
                        onFinish = {
                            scope.launch {
                                prefs.setOnboardingShown()

                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                                }
                            }
                        }
                    )
                }

                composable(Screen.Welcome.route) {
                    WelcomeScreen(
                        onLoginClick = {
                            navController.navigate(Screen.Login.route)
                        },
                        onSignUpClick = {}
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
                            scope.launch {
                                prefs.saveToken("mock_token")

                                navController.navigate(Screen.Main.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
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
