package com.sweeteri.stepikclient.screens.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sweeteri.stepikclient.data.repository.AuthRepository

@Composable
fun StartScreen(
    authRepository: AuthRepository,
    onNavigate: (String) -> Unit
) {
    val viewModel: StartViewModel = viewModel(
        factory = StartViewModelFactory(authRepository)
    )
    val startDestination by viewModel.startDestination.collectAsState()

    if (startDestination == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LaunchedEffect(startDestination) {
            onNavigate(startDestination!!)
        }
    }
}