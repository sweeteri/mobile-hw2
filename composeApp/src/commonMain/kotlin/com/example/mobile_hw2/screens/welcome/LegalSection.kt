package com.example.mobile_hw2.screens.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.mobile_hw2.generated.resources.Res
import com.example.mobile_hw2.generated.resources.email_opt_in
import com.example.mobile_hw2.generated.resources.legal_middle
import com.example.mobile_hw2.generated.resources.legal_prefix
import com.example.mobile_hw2.generated.resources.privacy_policy
import com.example.mobile_hw2.generated.resources.user_agreement
import org.jetbrains.compose.resources.stringResource

@Composable
fun LegalSection(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.widthIn(max = 420.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LegalText(
            onUserAgreementClick = {},
            onPrivacyPolicyClick = {}
        )
        Spacer(Modifier.height(12.dp))
        EmailOptInCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun EmailOptInCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = stringResource(Res.string.email_opt_in),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LegalText(
    onUserAgreementClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit
) {
    val prefix = stringResource(Res.string.legal_prefix).trim()
    val ua = stringResource(Res.string.user_agreement).trim()
    val middle = stringResource(Res.string.legal_middle).trim()
    val pp = stringResource(Res.string.privacy_policy).trim()
    val text = buildAnnotatedString {
        append("$prefix ")

        pushStringAnnotation(tag = "UA", annotation = "ua")
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(ua)
        }
        pop()

        append(" $middle ")

        pushStringAnnotation(tag = "PP", annotation = "pp")
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(pp)
        }
        pop()
    }

    ClickableText(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        ),
        onClick = { offset ->
            text.getStringAnnotations(offset, offset)
                .firstOrNull()
                ?.let {
                    when (it.tag) {
                        "UA" -> onUserAgreementClick()
                        "PP" -> onPrivacyPolicyClick()
                    }
                }
        },
        softWrap = true,
    )
}