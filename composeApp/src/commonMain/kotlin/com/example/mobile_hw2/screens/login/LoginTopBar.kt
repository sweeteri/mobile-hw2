package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.ic_reddit
import com.example.mobile_hw2.generated.resources.sign_up
import com.example.mobile_hw2.ui.theme.RedditTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginTopBar(
    onBackClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = (-12).dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = RedditTheme.colors.textSecondary
            )
        }

        Image(
            painter = painterResource(Res.drawable.ic_reddit),
            contentDescription = "Reddit",
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
        )


        TextButton(
            onClick = onSignUpClick,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = 12.dp)
        ) {
            Text(
                stringResource(Res.string.sign_up),
                color = RedditTheme.colors.textSecondary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}