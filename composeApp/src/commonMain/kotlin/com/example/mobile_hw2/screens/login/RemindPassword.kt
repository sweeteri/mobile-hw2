package com.example.mobile_hw2.screens.login

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.remind_password
import com.example.mobile_hw2.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun RemindPasswordButton() {
    TextButton(onClick = { /* remind password */ }) {
        Text(stringResource(Res.string.remind_password), color = StepikTheme.colors.accent)
    }
}