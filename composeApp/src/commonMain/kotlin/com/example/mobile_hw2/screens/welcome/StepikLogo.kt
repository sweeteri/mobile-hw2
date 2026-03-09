package com.example.mobile_hw2.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.ic_stepik
import com.example.mobile_hw2.generated.resources.stepik
import com.example.mobile_hw2.ui.theme.StepikTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StepikLogo() {
    Surface(
        modifier = Modifier.size(56.dp),
        shape = RoundedCornerShape(14.dp),
        color = StepikTheme.colors.accent
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_stepik),
            contentDescription = stringResource(Res.string.stepik),
            modifier = Modifier.size(48.dp)
        )
    }
}