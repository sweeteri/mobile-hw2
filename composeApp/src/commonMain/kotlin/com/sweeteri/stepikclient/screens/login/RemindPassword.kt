package com.sweeteri.stepikclient.screens.login

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.login_remind_password
import com.sweeteri.stepikclient.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun RemindPasswordButton() {
    TextButton(onClick = { /* remind password */ }) {
        Text(stringResource(Res.string.login_remind_password), color = StepikTheme.colors.accent)
    }
}