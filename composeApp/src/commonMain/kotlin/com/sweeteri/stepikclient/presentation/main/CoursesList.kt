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
import com.sweeteri.stepikclient.presentation.common.model.CourseUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesList(
    items: List<CourseUiModel>,
    isLoading: Boolean,
    error: String?,
    listState: LazyListState,
    onRetryPagination: () -> Unit
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        items(
            items = items,
            key = { it.id }
        ) { course ->
            CourseCard(course)
        }

        item {
            when {
                isLoading -> {
                    PaginationLoader()
                }

                error != null -> {
                    PaginationErrorRow(onRetryPagination)
                }
            }
        }
    }
}
