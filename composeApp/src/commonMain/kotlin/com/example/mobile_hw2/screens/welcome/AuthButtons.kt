package com.example.mobile_hw2.screens.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.AccountCircle

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

import org.jetbrains.compose.resources.stringResource
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.continue_phone
import com.example.mobile_hw2.generated.resources.continue_google
import com.example.mobile_hw2.generated.resources.continue_email

@Composable
fun AuthButtons(
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.widthIn(max = 420.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RedditAuthButton(
            text = stringResource(Res.string.continue_phone),
            icon = Icons.Outlined.Phone,
            onClick = {}
        )

        Spacer(Modifier.height(12.dp))

        RedditAuthButton(
            text = stringResource(Res.string.continue_google),
            icon = Icons.Outlined.AccountCircle,
            onClick = {}
        )

        Spacer(Modifier.height(12.dp))

        RedditAuthButton(
            text = stringResource(Res.string.continue_email),
            icon = Icons.Outlined.Person,
            onClick = onLoginClick
        )
    }
}

@Composable
private fun RedditAuthButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Box(Modifier.fillMaxWidth()) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(20.dp)
            )

            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}