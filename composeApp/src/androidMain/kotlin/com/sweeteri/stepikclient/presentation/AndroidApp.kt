package com.sweeteri.stepikclient.presentation

import androidx.compose.runtime.Composable
import com.sweeteri.stepikclient.App
import com.sweeteri.stepikclient.AppPreferences
import com.sweeteri.stepikclient.presentation.auth.login.LoginViewModel
import com.sweeteri.stepikclient.presentation.main.MainViewModel
import com.sweeteri.stepikclient.presentation.profile.ProfileViewModel
import com.sweeteri.stepikclient.presentation.start.StartViewModel
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.koinViewModel


@Composable
fun AndroidApp() {

    val prefs: AppPreferences = getKoin().get()
    val mainViewModel: MainViewModel = koinViewModel()
    val profileViewModel: ProfileViewModel = koinViewModel()
    val loginViewModel: LoginViewModel = koinViewModel()
    val startViewModel: StartViewModel = koinViewModel()

    App(
        prefs = prefs,
        mainViewModel = mainViewModel,
        profileViewModel = profileViewModel,
        loginViewModel = loginViewModel,
        startViewModel = startViewModel
    )
}
