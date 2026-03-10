package com.example.mobile_hw2.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.cannot_load_data
import com.example.mobile_hw2.generated.resources.error_occured
import com.example.mobile_hw2.generated.resources.try_again
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorView(message: String?, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.error_occured),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = message ?: stringResource(Res.string.cannot_load_data),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onRetry,
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp)
        ) {
            Text(stringResource(Res.string.try_again))
        }
    }
}