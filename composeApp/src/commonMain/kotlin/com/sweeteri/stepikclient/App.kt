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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sweeteri.stepikclient.presentation.auth.login.LoginScreen
import com.sweeteri.stepikclient.presentation.auth.login.LoginViewModel
import com.sweeteri.stepikclient.presentation.auth.welcome.WelcomeScreen
import com.sweeteri.stepikclient.presentation.course.CourseDetailScreen
import com.sweeteri.stepikclient.presentation.navigation.MainContainer
import com.sweeteri.stepikclient.presentation.navigation.Screen
import com.sweeteri.stepikclient.presentation.onboarding.OnboardingScreen
import com.sweeteri.stepikclient.presentation.onboarding.OnboardingViewModel
import com.sweeteri.stepikclient.presentation.start.StartScreen
import com.sweeteri.stepikclient.presentation.start.StartViewModel
import com.sweeteri.stepikclient.presentation.ui.theme.StepikTheme
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun App() {
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
                    val startViewModel: StartViewModel = koinViewModel()
                    StartScreen(
                        viewModel = startViewModel,
                        onNavigate = { route ->
                            navController.navigate(route) {
                                popUpTo(Screen.Start.route) { inclusive = true }
                            }
                        }
                    )
                }
                composable(Screen.Onboarding.route) {
                    val onboardingViewModel: OnboardingViewModel = koinViewModel()
                    LaunchedEffect(Unit) {
                        onboardingViewModel.events.collect {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Onboarding.route) { inclusive = true }
                            }
                        }
                    }
                    OnboardingScreen(
                        onFinish = { onboardingViewModel.onFinish() }
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
                    val loginViewModel: LoginViewModel = koinViewModel()
                    LoginScreen(
                        viewModel = loginViewModel,
                        onBackClick = { navController.popBackStack() },
                        onRegisterClick = {
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        },
                        onLoginSuccess = {
                            navController.navigate(Screen.MainRoot.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Screen.MainRoot.route) {
                    MainContainer(rootNavController = navController)
                }
                composable(
                    route = Screen.CourseDetail.routeWithArg,
                    arguments = listOf(navArgument("courseId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
                    CourseDetailScreen(
                        courseId = courseId,
                        onBack = { navController.popBackStack() }
                    )
                }

            }
        }
    }
}