package com.example.mobile_hw2.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.search_courses
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchField(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp),
        placeholder = { Text(stringResource(Res.string.search_courses)) },
        leadingIcon = { Icon(Icons.Default.Search, null) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}