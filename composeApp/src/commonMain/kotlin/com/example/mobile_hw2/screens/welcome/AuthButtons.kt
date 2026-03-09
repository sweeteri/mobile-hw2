package com.example.mobile_hw2.screens.welcome

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.less
import com.example.mobile_hw2.generated.resources.more
import com.example.mobile_hw2.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthButtons() {

    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.animateContentSize()
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            firstRowSocials.forEach {
                SocialButton(
                    icon = it.icon,
                    contentDescription = it.name
                )
            }
        }

        if (expanded) {

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                secondRowSocials.forEach {
                    SocialButton(
                        icon = it.icon,
                        contentDescription = it.name
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = if (expanded) stringResource(Res.string.less) else stringResource(Res.string.more),
            color = StepikTheme.colors.accent,
            modifier = Modifier.clickable {
                expanded = !expanded
            }
        )
    }
}