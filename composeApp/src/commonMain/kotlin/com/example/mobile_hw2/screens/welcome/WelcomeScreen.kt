package com.example.mobile_hw2.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(64.dp))

        WelcomeHeader()

        Spacer(Modifier.height(32.dp))

        AuthButtons(
            onLoginClick = onLoginClick
        )

        Spacer(Modifier.weight(1f))

        LegalSection()

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 24.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
        )

        SignUpRow(
            onSignUpClick = onSignUpClick
        )

        Spacer(Modifier.height(24.dp))
    }
}