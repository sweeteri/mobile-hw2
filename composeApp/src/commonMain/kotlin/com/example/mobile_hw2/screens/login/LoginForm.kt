package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.forgot_password
import com.example.mobile_hw2.generated.resources.login_label
import com.example.mobile_hw2.generated.resources.login_placeholder
import com.example.mobile_hw2.generated.resources.password_label
import com.example.mobile_hw2.generated.resources.password_placeholder
import org.jetbrains.compose.resources.stringResource
import androidx.compose.foundation.layout.Column

@Composable
fun LoginForm(
    email: String,
    password: String,
    isPasswordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit
) {
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(Res.string.login_label)) },
            leadingIcon = { Icon(Icons.Default.Email, null) },
            placeholder = { Text(stringResource(Res.string.login_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(Res.string.password_label)) },
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            trailingIcon = {
                PasswordVisibilityIcon(
                    isVisible = isPasswordVisible,
                    onToggle = onTogglePasswordVisibility
                )
            },
            placeholder = { Text(stringResource(Res.string.password_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation =
                if (isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(Res.string.forgot_password))
        }
    }
}