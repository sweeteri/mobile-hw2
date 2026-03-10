package com.example.mobile_hw2.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.change_search
import com.example.mobile_hw2.generated.resources.nothing_found
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyStateView() {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Color.LightGray
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.nothing_found),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Text(
            text = stringResource(Res.string.change_search),
            fontSize = 14.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )
    }
}