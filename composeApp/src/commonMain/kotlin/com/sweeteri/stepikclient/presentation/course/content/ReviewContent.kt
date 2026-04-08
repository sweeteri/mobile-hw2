package com.sweeteri.stepikclient.presentation.course.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sweeteri.stepikclient.data.local.model.ReviewStats
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.detailscreen_reviews_empty
import com.sweeteri.stepikclient.presentation.common.components.PaginationErrorRow
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailIntent
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailState
import com.sweeteri.stepikclient.presentation.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource

fun LazyListScope.reviewsItems(
    state: CourseDetailState,
    onIntent: (CourseDetailIntent) -> Unit
) {
    val course = state.course ?: return

    val filtered = if (state.selectedRating != null) {
        state.reviews.filter { it.score.toInt() == state.selectedRating }
    } else state.reviews

    item {
        ReviewSummary(
            stats = ReviewStats(
                total = course.reviewCount,
                average = course.average.toFloat(),
                distribution = course.distribution
            ),
            selectedRating = state.selectedRating,
            onRatingSelected = {
                onIntent(CourseDetailIntent.SelectRating(it))
            }
        )
    }

    if (state.isReviewsLoading && filtered.isEmpty()) {
        items(5) {
            ReviewSkeleton()
        }
    } else {
        items(filtered) { review ->
            ReviewItem(review)
        }
    }


    if (state.isReviewsLoading && filtered.isNotEmpty()) {
        item {
            Box(
                Modifier.fillMaxWidth().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    if (state.reviewsError != null) {
        item {
            PaginationErrorRow(
                onRetry = { onIntent(CourseDetailIntent.LoadNextReviews) }
            )
        }
    }

    if (!state.isReviewsLoading && filtered.isEmpty() && state.reviewsError == null) {
        item {
            Text(
                text = stringResource(Res.string.detailscreen_reviews_empty),
                modifier = Modifier.padding(16.dp),
                color = StepikTheme.colors.textSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}