package com.example.mobile_hw2.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.sign_in_email
import com.example.mobile_hw2.screens.welcome.StepikLogo
import com.example.mobile_hw2.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        StepikLogo()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.sign_in_email),
            color = StepikTheme.colors.textSecondary,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}