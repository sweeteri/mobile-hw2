package com.sweeteri.stepikclient.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.home_error_button
import com.sweeteri.stepikclient.generated.resources.home_error_title
import com.sweeteri.stepikclient.generated.resources.home_search_empty_message
import com.sweeteri.stepikclient.generated.resources.home_search_empty_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun FullScreenStateOverlay(
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            isLoading -> CircularProgressIndicator()
            error != null -> ErrorView(
                title = stringResource(Res.string.home_error_title),
                message = error,
                retryMessage = stringResource(Res.string.home_error_button),
                onRetry = onRetry
            )

            else -> {
                EmptyStateView(
                    title = stringResource(Res.string.home_search_empty_title),
                    message = stringResource(Res.string.home_search_empty_message)
                )
            }
        }
    }
}