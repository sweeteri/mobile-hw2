package com.sweeteri.stepikclient.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sweeteri.stepikclient.presentation.main.MainScreen
import com.sweeteri.stepikclient.presentation.main.MainViewModel
import com.sweeteri.stepikclient.presentation.profile.ProfileScreen
import com.sweeteri.stepikclient.presentation.profile.ProfileViewModel
import com.sweeteri.stepikclient.presentation.search.SearchScreen
import com.sweeteri.stepikclient.presentation.search.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainContainer(rootNavController: NavHostController) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    Scaffold(

        bottomBar = {

            BottomBar(
                currentRoute = currentRoute,
                onItemClick = { screen ->
                    navController.navigate(screen.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { fullPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(fullPadding)
        ) {

            composable(Screen.Home.route) {
                val mainViewModel: MainViewModel = koinViewModel()
                MainScreen(
                    viewModel = mainViewModel,
                    navController = rootNavController
                    )
            }

            composable(Screen.Profile.route) {
                val profileViewModel: ProfileViewModel = koinViewModel()
                ProfileScreen(
                    viewModel = profileViewModel,
                    onLogout = {
                        rootNavController.navigate(Screen.Login.route) {
                            popUpTo(Screen.MainRoot.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Search.route) {
                val searchViewModel: SearchViewModel = koinViewModel()
                SearchScreen(viewModel = searchViewModel,
                    navController = rootNavController)
            }

            composable(Screen.Notifications.route) {
                //NotificationsScreen()
            }
        }
    }
}