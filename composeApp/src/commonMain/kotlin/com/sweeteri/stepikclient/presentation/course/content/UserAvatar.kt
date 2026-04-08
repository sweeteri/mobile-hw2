package com.sweeteri.stepikclient.presentation.course.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sweeteri.stepikclient.presentation.course.utils.toAvatarColor
import com.sweeteri.stepikclient.presentation.course.utils.toInitials

@Composable
fun UserAvatar(
    name: String,
    avatarUrl: String?,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(8.dp)
    val initials = name.toInitials()
    val color = name.toAvatarColor()

    var isError by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(shape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {

        if (!avatarUrl.isNullOrBlank() && !isError) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = name,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
                onError = { isError = true }
            )
        } else {
            Text(
                text = initials,
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}