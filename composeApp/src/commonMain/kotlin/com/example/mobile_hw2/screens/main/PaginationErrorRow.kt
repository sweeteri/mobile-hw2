package com.example.mobile_hw2.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.cannot_load_data
import com.example.mobile_hw2.generated.resources.try_again
import org.jetbrains.compose.resources.stringResource

@Composable
fun PaginationErrorRow(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(Res.string.cannot_load_data), color = Color.Red)
        Button(onClick = onRetry) { Text(stringResource(Res.string.try_again)) }
    }
}