package com.example.mobile_hw2.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.mobile_hw2.data.model.Course

fun formatLearners(count: Int): String {
    return when {
        count >= 1000 -> {
            val thousands = count / 1000.0
            val rounded = kotlin.math.round(thousands * 10) / 10.0
            "${rounded}k"
        }

        else -> count.toString()
    }
}


@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = course.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = course.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = course.author,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = if (course.average > 0) Color.Yellow else Color.Gray
                    )
                    Text(
                        text = if (course.average > 0) " ${course.average}" else " —",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (course.average > 0) Color.Unspecified else Color.Gray
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = " ${formatLearners(course.learnersCount)}",
                        style = MaterialTheme.typography.labelMedium
                    )
                }

            }
        }
    }
}
