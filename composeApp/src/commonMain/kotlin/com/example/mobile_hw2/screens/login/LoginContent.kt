package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginContent(
    username: String,
    password: String,
    isPasswordVisible: Boolean,
    isLoginButtonEnabled: Boolean,
    error: String? = null,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {

        Spacer(Modifier.height(16.dp))

        LoginTopBar(
            onBackClick = onBackClick,
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LoginHeader()

            Spacer(modifier = Modifier.height(24.dp))

            LoginForm(
                username = username,
                password = password,
                isPasswordVisible = isPasswordVisible,
                onUsernameChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onTogglePasswordVisibility = onTogglePasswordVisibility
            )

            if (!error.isNullOrEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(16.dp))

            LoginButton(
                enabled = isLoginButtonEnabled,
                onClick = onLoginClick
            )

            Spacer(modifier = Modifier.height(8.dp))

            RemindPasswordButton()
        }

        Spacer(modifier = Modifier.weight(1f))

        SignUpSection(
            onLoginClick = onRegisterClick,
            onSignUpClick = onRegisterClick
        )
    }
}