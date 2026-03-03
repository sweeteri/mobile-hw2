package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.login_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginHeader() {
    Spacer(Modifier.height(32.dp))

    Text(
        text = stringResource(Res.string.login_screen_title),
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.ExtraBold,
    )
    Spacer(Modifier.height(32.dp))
}