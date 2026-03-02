package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.create_account_button
import com.example.mobile_hw2.generated.resources.login_button
import com.example.mobile_hw2.generated.resources.no_account_text
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginActions(
    isLoginButtonEnabled: Boolean,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Spacer(Modifier.height(32.dp))

    Button(
        onClick = onLoginClick,
        enabled = isLoginButtonEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(stringResource(Res.string.login_button))
    }

    Spacer(Modifier.height(24.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.no_account_text),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        TextButton(onClick = onRegisterClick) {
            Text(
                text = stringResource(Res.string.create_account_button),
                fontWeight = FontWeight.Bold
            )
        }
    }

    Spacer(Modifier.height(32.dp))
}