package com.sweeteri.stepikclient.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sweeteri.stepikclient.presentation.common.components.PaginationLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesList(
    state: MainUiState,
    listState: LazyListState,
    onRetryPagination: () -> Unit
) {

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        items(items = state.courses, key = { it.id }) { course ->
            CourseCard(course)
        }

        if (state.courses.isNotEmpty()) {
            if (state.isLoading) {
                item { PaginationLoader() }
            } else if (state.error != null) {
                item { PaginationErrorRow(onRetryPagination) }
            }
        }
    }
}

