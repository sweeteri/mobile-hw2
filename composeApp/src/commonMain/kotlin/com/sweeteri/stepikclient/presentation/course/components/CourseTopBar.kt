package com.sweeteri.stepikclient.presentation.course.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.detailscreen_back
import com.sweeteri.stepikclient.generated.resources.detailscreen_favorite
import com.sweeteri.stepikclient.generated.resources.detailscreen_menu
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = stringResource(Res.string.detailscreen_back))
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(Res.string.detailscreen_favorite)
                )
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.MoreVert, contentDescription = stringResource(Res.string.detailscreen_menu))
            }
        }
    )
}
