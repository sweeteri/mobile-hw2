package com.sweeteri.stepikclient.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sweeteri.stepikclient.presentation.common.components.EmptyStateView
import com.sweeteri.stepikclient.presentation.common.components.ErrorView

@Composable
fun FullScreenStateOverlay(
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            isLoading -> CircularProgressIndicator()
            error != null -> ErrorView(message = error, onRetry = onRetry)
            else -> {
                EmptyStateView()
            }
        }
    }
}