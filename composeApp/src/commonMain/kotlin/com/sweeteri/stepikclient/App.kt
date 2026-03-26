package com.sweeteri.stepikclient

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sweeteri.stepikclient.presentation.auth.login.LoginScreen
import com.sweeteri.stepikclient.presentation.auth.login.LoginViewModel
import com.sweeteri.stepikclient.presentation.auth.welcome.WelcomeScreen
import com.sweeteri.stepikclient.presentation.common.theme.StepikTheme
import com.sweeteri.stepikclient.presentation.main.MainScreen
import com.sweeteri.stepikclient.presentation.main.MainViewModel
import com.sweeteri.stepikclient.presentation.navigation.Screen
import com.sweeteri.stepikclient.presentation.profile.ProfileScreen
import com.sweeteri.stepikclient.presentation.profile.ProfileViewModel
import com.sweeteri.stepikclient.presentation.start.StartScreen


@Composable
fun App(
    mainViewModel: MainViewModel,
    profileViewModel: ProfileViewModel,
    loginViewModel: LoginViewModel,
    startViewModel: StartViewModel
) {
    StepikTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.Start.route,
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
                composable(Screen.Start.route) {
                    StartScreen(
                        viewModel = startViewModel,
                        onNavigate = { route ->
                            navController.navigate(route) {
                                popUpTo(Screen.Start.route) { inclusive = true }
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
                        viewModel = loginViewModel,
                        onBackClick = { navController.popBackStack() },
                        onRegisterClick = {
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        },
                        onLoginSuccess = {
                            navController.navigate(Screen.Main.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Screen.Main.route) {
                    MainScreen(
                        viewModel = mainViewModel,
                        onProfileClick = {
                            navController.navigate(Screen.Profile.route)
                        }
                    )
                }
                composable(Screen.Profile.route) {
                    ProfileScreen(
                        viewModel = profileViewModel,
                        onLogout = {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Main.route) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
