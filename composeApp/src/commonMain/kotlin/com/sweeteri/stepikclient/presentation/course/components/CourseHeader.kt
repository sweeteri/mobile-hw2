package com.sweeteri.stepikclient.presentation.course.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.detailscreen_button_continue
import com.sweeteri.stepikclient.generated.resources.detailscreen_button_enroll
import com.sweeteri.stepikclient.generated.resources.detailscreen_learners_count
import com.sweeteri.stepikclient.generated.resources.detailscreen_lessons_count
import com.sweeteri.stepikclient.generated.resources.detailscreen_reviews_count
import com.sweeteri.stepikclient.presentation.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource


@Composable
fun CourseHeader(
    course: CourseDetail,
    onEnrollClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Button(
            onClick = onEnrollClick,
            enabled = !course.isEnrolled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (course.isEnrolled) stringResource(Res.string.detailscreen_button_continue) else stringResource(
                    Res.string.detailscreen_button_enroll
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${course.average} ★", style = MaterialTheme.typography.bodyMedium)

            Text(
                stringResource(Res.string.detailscreen_reviews_count, course.reviewCount),
                style = MaterialTheme.typography.bodySmall,
                color = StepikTheme.colors.textSecondary
            )

            Text(
                stringResource(Res.string.detailscreen_lessons_count, course.lessonCount),
                style = MaterialTheme.typography.bodySmall,
                color = StepikTheme.colors.textSecondary
            )

            Text(
                stringResource(Res.string.detailscreen_learners_count, course.learnersCount),
                style = MaterialTheme.typography.bodySmall,
                color = StepikTheme.colors.textSecondary
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.Top) {

            if (course.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = course.imageUrl,
                    contentDescription = course.title,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                course.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
