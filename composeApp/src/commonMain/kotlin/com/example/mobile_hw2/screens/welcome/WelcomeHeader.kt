package com.example.mobile_hw2.screens.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.runtime.Composable

import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

import org.jetbrains.compose.resources.stringResource
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.login_title

@Composable
fun WelcomeHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "👽",
            fontSize = 48.sp
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.login_title),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}
