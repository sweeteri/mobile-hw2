package com.example.mobile_hw2.screens.login


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.login_screen_title
import com.example.mobile_hw2.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = StepikTheme.colors.textFieldBackground),
        enabled = enabled
    ) {
        Text(stringResource(Res.string.login_screen_title), color = StepikTheme.colors.accent)
    }
}