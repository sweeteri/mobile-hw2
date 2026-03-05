package com.example.mobile_hw2.screens.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.sign_in_general
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StepikLogo()

        Spacer(Modifier.height(32.dp))

        Text(
            text = stringResource(Res.string.sign_in_general),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
