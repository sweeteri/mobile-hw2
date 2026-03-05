package com.example.mobile_hw2.screens.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.sign_in_general
import com.example.mobile_hw2.generated.resources.sign_up
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpSection(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TextButton(
            onClick = onLoginClick
        ) {
            Text(
                text = stringResource(Res.string.sign_in_general),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        TextButton(
            onClick = onSignUpClick
        ) {
            Text(
                text = stringResource(Res.string.sign_up),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}