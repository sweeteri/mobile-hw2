package com.sweeteri.stepikclient.presentation.navigation

import BottomBarScreen
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun BottomBar(
    currentRoute: String?,
    onItemClick: (BottomBarScreen) -> Unit
) {
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Notifications,
        BottomBarScreen.Profile
    )

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { onItemClick(screen) },
                icon = { Icon(screen.icon, contentDescription = screen.route) },
                label = { Text(screen.route) }
            )
        }
    }
}