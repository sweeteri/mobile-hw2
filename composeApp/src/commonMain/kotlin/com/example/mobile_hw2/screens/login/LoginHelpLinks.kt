package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.email_link
import com.example.mobile_hw2.generated.resources.forgot_password
import com.example.mobile_hw2.ui.theme.RedditTheme
import org.jetbrains.compose.resources.stringResource


@Composable
fun LoginHelpLinks() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = {}) {
            Text(
                stringResource(Res.string.forgot_password),
                color = RedditTheme.colors.accentOrange,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        TextButton(onClick = {}) {
            Text(
                stringResource(Res.string.email_link),
                color = RedditTheme.colors.accentOrange,  // Рыжий цвет из темы
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}