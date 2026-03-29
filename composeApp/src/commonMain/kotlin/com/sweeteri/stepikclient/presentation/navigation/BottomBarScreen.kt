import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(val route: String, val icon: ImageVector) {
    object Home : BottomBarScreen("home", Icons.Default.Home)
    object Search : BottomBarScreen("search", Icons.Default.Search)
    object Notifications : BottomBarScreen("notifications", Icons.Default.Notifications)
    object Profile : BottomBarScreen("profile", Icons.Default.Person)
}