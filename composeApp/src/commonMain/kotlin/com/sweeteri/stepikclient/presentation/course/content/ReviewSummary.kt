package com.sweeteri.stepikclient.presentation.course.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sweeteri.stepikclient.data.local.model.ReviewStats
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.detailscreen_out_of_5
import com.sweeteri.stepikclient.generated.resources.detailscreen_reviews_count
import com.sweeteri.stepikclient.presentation.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun ReviewSummary(
    stats: ReviewStats,
    selectedRating: Int?,
    onRatingSelected: (Int?) -> Unit
) {
    val rowHeight = 20.dp
    val spacing = 8.dp
    val totalHeight = rowHeight * 5 + spacing * 4
    val textSecondary = StepikTheme.colors.textSecondary
    val accent = StepikTheme.colors.accent
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .width(100.dp)
                    .height(totalHeight),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val rounded = (stats.average * 10).toInt() / 10.0
                Text(
                    text = rounded.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(4.dp))

                Row {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < stats.average.toInt())
                                accent
                            else textSecondary.copy(alpha = 0.3f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(totalHeight),
                verticalArrangement = Arrangement.spacedBy(spacing)
            ) {
                (5 downTo 1).forEach { star ->
                    val count = stats.distribution[star] ?: 0
                    val percent = if (stats.total > 0) count.toFloat() / stats.total else 0f
                    val isSelected = selectedRating == star

                    Row(
                        modifier = Modifier
                            .height(rowHeight)
                            .clickable { onRatingSelected(if (isSelected) null else star) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    if (isSelected) accent.copy(alpha = 0.3f) else textSecondary.copy(
                                        alpha = 0.2f
                                    )
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(percent)
                                    .background(accent)
                            )
                        }

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "$count",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.width(32.dp),
                            color = textSecondary
                        )

                        Spacer(Modifier.width(4.dp))

                        Row(modifier = Modifier.width(70.dp)) {
                            repeat(star) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = accent,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.width(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(Res.string.detailscreen_out_of_5),
                    style = MaterialTheme.typography.bodySmall,
                    color = textSecondary
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                text = stringResource(Res.string.detailscreen_reviews_count, stats.total),
                style = MaterialTheme.typography.bodySmall,
                color = textSecondary
            )
        }
    }
}
