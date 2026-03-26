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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sweeteri.stepikclient.data.repository.AuthRepository
import com.sweeteri.stepikclient.data.repository.CoursesRepository
import com.sweeteri.stepikclient.data.repository.LoginRepository
import com.sweeteri.stepikclient.data.repository.ProfileRepository
import com.sweeteri.stepikclient.navigation.Screen
import com.sweeteri.stepikclient.screens.login.LoginScreen
import com.sweeteri.stepikclient.screens.main.MainScreen
import com.sweeteri.stepikclient.screens.main.MainViewModel
import com.sweeteri.stepikclient.screens.onboarding.OnboardingScreen
import com.sweeteri.stepikclient.screens.profile.ProfileScreen
import com.sweeteri.stepikclient.screens.start.StartScreen
import com.sweeteri.stepikclient.screens.welcome.WelcomeScreen
import com.sweeteri.stepikclient.ui.theme.StepikTheme
import kotlinx.coroutines.launch


@Composable
fun App(
    coursesRepository: CoursesRepository,
    profileRepository: ProfileRepository,
    authRepository: AuthRepository,
    loginRepository: LoginRepository
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
                        authRepository = authRepository,
                        onNavigate = { route ->
                            navController.navigate(route) {
                                popUpTo(Screen.Start.route) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Screen.Onboarding.route) {
                    val scope = rememberCoroutineScope()
                    OnboardingScreen(
                        onFinish = {
                            scope.launch {
                                authRepository.setOnboardingShown()
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
                        loginRepository = loginRepository,
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
                    val viewModel: MainViewModel = viewModel {
                        MainViewModel(coursesRepository)
                    }

                    MainScreen(
                        viewModel = viewModel,
                        onProfileClick = { navController.navigate(Screen.Profile.route) } // 🔹
                    )
                }
                composable(Screen.Profile.route) {
                    ProfileScreen(
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
