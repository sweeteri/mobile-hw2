package com.sweeteri.stepikclient.presentation.course.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.sweeteri.stepikclient.data.local.mapper.mapLanguage
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.detailscreen_authors
import com.sweeteri.stepikclient.generated.resources.detailscreen_certificate_available
import com.sweeteri.stepikclient.generated.resources.detailscreen_certificate_distinction
import com.sweeteri.stepikclient.generated.resources.detailscreen_certificate_from
import com.sweeteri.stepikclient.generated.resources.detailscreen_certificate_not_available
import com.sweeteri.stepikclient.generated.resources.detailscreen_course_info
import com.sweeteri.stepikclient.generated.resources.detailscreen_description
import com.sweeteri.stepikclient.generated.resources.detailscreen_duration
import com.sweeteri.stepikclient.generated.resources.detailscreen_free
import com.sweeteri.stepikclient.generated.resources.detailscreen_language
import com.sweeteri.stepikclient.generated.resources.detailscreen_lessons
import com.sweeteri.stepikclient.generated.resources.detailscreen_paid
import com.sweeteri.stepikclient.generated.resources.detailscreen_price
import com.sweeteri.stepikclient.generated.resources.detailscreen_requirements
import com.sweeteri.stepikclient.generated.resources.detailscreen_skills
import com.sweeteri.stepikclient.generated.resources.detailscreen_summary
import com.sweeteri.stepikclient.generated.resources.detailscreen_target_audience
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailState
import com.sweeteri.stepikclient.presentation.course.utils.formatDuration
import com.sweeteri.stepikclient.presentation.course.utils.toAnnotatedHtml
import org.jetbrains.compose.resources.stringResource


fun LazyListScope.overviewItems(state: CourseDetailState) {
    val course = state.course ?: return

    if (course.summary.isNotBlank()) {
        item {
            SectionBlock(stringResource(Res.string.detailscreen_summary)) {
                Text(course.summary.toAnnotatedHtml())
            }
        }
    }

    if (course.description.isNotBlank()) {
        item {
            SectionBlock(stringResource(Res.string.detailscreen_description)) {
                Text(course.description.toAnnotatedHtml())
            }
        }
    }

    if (course.acquiredSkills.isNotEmpty()) {
        item {
            SectionBlock(stringResource(Res.string.detailscreen_skills)) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    course.acquiredSkills.forEach {
                        AssistChip(
                            onClick = {},
                            label = { Text(it) }
                        )
                    }
                }
            }
        }
    }

    if (course.requirements.isNotBlank()) {
        item {
            SectionBlock(stringResource(Res.string.detailscreen_requirements)) {
                Text(course.requirements.toAnnotatedHtml())
            }
        }
    }

    if (course.targetAudience.isNotBlank()) {
        item {
            SectionBlock(stringResource(Res.string.detailscreen_target_audience)) {
                Text(course.targetAudience.toAnnotatedHtml())
            }
        }
    }

    if (course.authors.isNotEmpty()) {
        item {
            SectionBlock(stringResource(Res.string.detailscreen_authors)) {
                Column {
                    course.authors.forEach {
                        Text(it.fullName)
                    }
                }
            }
        }
    }

    item {
        SectionBlock(stringResource(Res.string.detailscreen_course_info)) {

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(stringResource(Res.string.detailscreen_duration, formatDuration(course.timeToComplete)))

                Text(stringResource(Res.string.detailscreen_lessons, course.lessonCount))


                if (course.language.isNotBlank()) {
                    Text(stringResource(Res.string.detailscreen_language, mapLanguage(course.language)))
                }

                val priceText = when {
                    !course.pricing.isPaid -> stringResource(Res.string.detailscreen_free)
                    course.pricing.displayPrice.isNotBlank() -> course.pricing.displayPrice
                    else -> stringResource(Res.string.detailscreen_paid)
                }
                Text(stringResource(Res.string.detailscreen_price, priceText))

                if (course.isCertificateIssued) {
                    Text(stringResource(Res.string.detailscreen_certificate_available))

                    course.certificateRegularThreshold?.let {
                        Text(stringResource(Res.string.detailscreen_certificate_from, it.toString()))
                    }

                    course.certificateDistinctionThreshold?.let {
                        Text(stringResource(Res.string.detailscreen_certificate_distinction, it.toString()))
                    }
                } else {
                    Text(stringResource(Res.string.detailscreen_certificate_not_available))
                }
            }
        }
    }
}
