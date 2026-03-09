package com.example.mobile_hw2.screens.login

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.hide_password
import com.example.mobile_hw2.generated.resources.show_password
import org.jetbrains.compose.resources.stringResource

@Composable
fun PasswordVisibilityIcon(
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