package com.example.mobile_hw2.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
expect fun BackHandlerWithExit()

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BackHandlerWithExit()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Home",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        items(
            items = state.courses,
            key = { it.id }
        ) { course ->
            CourseCard(course)
        }
    }
}