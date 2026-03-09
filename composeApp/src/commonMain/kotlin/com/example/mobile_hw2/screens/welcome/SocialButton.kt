package com.example.mobile_hw2.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SocialButton(
    icon: DrawableResource,
    contentDescription: String
) {
    Surface(
        modifier = Modifier.size(64.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}