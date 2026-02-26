package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.verticalScroll

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.Row

import org.jetbrains.compose.resources.stringResource
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.back_button_description
import com.example.mobile_hw2.generated.resources.create_account_button
import com.example.mobile_hw2.generated.resources.forgot_password
import com.example.mobile_hw2.generated.resources.login_button
import com.example.mobile_hw2.generated.resources.login_label
import com.example.mobile_hw2.generated.resources.login_placeholder
import com.example.mobile_hw2.generated.resources.login_screen_title
import com.example.mobile_hw2.generated.resources.no_account_text
import com.example.mobile_hw2.generated.resources.password_label
import com.example.mobile_hw2.generated.resources.password_placeholder
import com.example.mobile_hw2.generated.resources.hide_password
import com.example.mobile_hw2.generated.resources.show_password

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onRegisterClick: () -> Unit = {},
    onBackClick: () -> Unit
) {
    LoginContent(
        email = viewModel.email,
        password = viewModel.password,
        isPasswordVisible = viewModel.isPasswordVisible,
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onTogglePasswordVisibility = { viewModel.togglePasswordVisibility() },
        onLoginClick = { viewModel.login() },
        onRegisterClick = onRegisterClick,
        onBackClick = onBackClick
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginContent(
    email: String,
    password: String,
    isPasswordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.back_button_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(Res.string.login_screen_title),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text(stringResource(Res.string.login_label)) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                placeholder = { Text(stringResource(Res.string.login_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = { Text(stringResource(Res.string.password_label)) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    PasswordVisibilityIcon(
                        isVisible = isPasswordVisible,
                        onToggle = onTogglePasswordVisibility
                    )
                },
                placeholder = { Text(stringResource(Res.string.password_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                singleLine = true,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            TextButton(
                onClick = { /* Напомнить пароль */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    stringResource(Res.string.forgot_password),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    stringResource(Res.string.login_button),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.no_account_text),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                TextButton(onClick = onRegisterClick) {
                    Text(
                        text = stringResource(Res.string.create_account_button),
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun PasswordVisibilityIcon(
    isVisible: Boolean,
    onToggle: () -> Unit
) {
    IconButton(onClick = onToggle) {
        val (icon, description) = if (isVisible) {
            Icons.Default.Visibility to stringResource(Res.string.hide_password)
        } else {
            Icons.Default.VisibilityOff to stringResource(Res.string.show_password)
        }
        Icon(
            imageVector = icon,
            contentDescription = description
        )
    }
}

