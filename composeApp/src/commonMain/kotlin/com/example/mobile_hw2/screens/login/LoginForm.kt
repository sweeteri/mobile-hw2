package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.login_label
import com.example.mobile_hw2.generated.resources.password_label
import com.example.mobile_hw2.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource


@Composable
fun LoginForm(
    username: String,
    password: String,
    isPasswordVisible: Boolean,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit
) {
    val accent = StepikTheme.colors.accent
    val textSecondary = StepikTheme.colors.textSecondary
    val borderColor = textSecondary.copy(alpha = 0.3f)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
    ) {
        TextField(
            value = username,
            onValueChange = onUsernameChange,
            placeholder = { Text(stringResource(Res.string.login_label), color = textSecondary) },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldTransparentColors(accent),
            singleLine = true
        )

        Divider(
            color = borderColor,
            thickness = 0.5.dp
        )

        TextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {
                Text(
                    stringResource(Res.string.password_label),
                    color = textSecondary
                )
            },
            visualTransformation =
                if (isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            trailingIcon = {
                PasswordVisibilityIcon(
                    isVisible = isPasswordVisible,
                    onToggle = onTogglePasswordVisibility
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldTransparentColors(accent),
            singleLine = true
        )
    }
}

@Composable
private fun textFieldTransparentColors(cursorColor: Color) =
    TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,

        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,

        cursorColor = cursorColor,

        focusedTextColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTextColor = MaterialTheme.colorScheme.onBackground
    )