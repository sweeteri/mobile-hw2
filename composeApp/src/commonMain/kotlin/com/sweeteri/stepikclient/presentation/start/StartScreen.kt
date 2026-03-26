package com.sweeteri.stepikclient.presentation.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StartScreen(
    viewModel: StartViewModel,
    onNavigate: (String) -> Unit
) {
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