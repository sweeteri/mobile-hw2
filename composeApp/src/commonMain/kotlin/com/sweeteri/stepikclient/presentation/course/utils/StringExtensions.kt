package com.sweeteri.stepikclient.presentation.course.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_1
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_10
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_11
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_12
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_2
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_3
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_4
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_5
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_6
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_7
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_8
import com.sweeteri.stepikclient.generated.resources.detailscreen_month_9
import com.sweeteri.stepikclient.presentation.ui.theme.StepikTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.math.absoluteValue

fun String.toInitials(): String {
    return this.split(" ")
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .take(2)
        .joinToString("")
}

@Composable
fun String.toAvatarColor(): Color {
    val colors = StepikTheme.colors.avatarColors
    val index = this.hashCode().absoluteValue % colors.size
    return colors[index]
}


fun String.toReadableDateRaw(): Triple<Int, Int, Int> {
    return try {
        val instant = Instant.parse(this)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        Triple(dateTime.dayOfMonth, dateTime.monthNumber, dateTime.year)
    } catch (e: Exception) {
        Triple(0, 0, 0)
    }
}

@Composable
fun String.toReadableDate(): String {
    val (day, monthNumber, year) = this.toReadableDateRaw()

    if (day == 0 || monthNumber == 0 || year == 0) return this

    val monthName = stringResource(
        when (monthNumber) {
            1 -> Res.string.detailscreen_month_1
            2 -> Res.string.detailscreen_month_2
            3 -> Res.string.detailscreen_month_3
            4 -> Res.string.detailscreen_month_4
            5 -> Res.string.detailscreen_month_5
            6 -> Res.string.detailscreen_month_6
            7 -> Res.string.detailscreen_month_7
            8 -> Res.string.detailscreen_month_8
            9 -> Res.string.detailscreen_month_9
            10 -> Res.string.detailscreen_month_10
            11 -> Res.string.detailscreen_month_11
            12 -> Res.string.detailscreen_month_12
            else -> Res.string.detailscreen_month_1
        }
    )

    val instant = Instant.parse(this)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val hour = dateTime.hour.toString().padStart(2, '0')
    val minute = dateTime.minute.toString().padStart(2, '0')

    return "$day $monthName $year $hour:$minute"
}

fun String.decodeHtml(): String {
    return this
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&amp;", "&")
        .replace("&quot;", "\"")
        .replace("&#39;", "'")
}


fun String.toAnnotatedHtml(): AnnotatedString {
    val cleaned = this
        .replace("<br>", "\n")
        .replace("<br/>", "\n")
        .replace("</p>", "\n\n")
        .replace("<li>", "• ")
        .replace("</li>", "\n")

    val builder = AnnotatedString.Builder()

    var i = 0
    var isStartOfLine = true

    while (i < cleaned.length) {

        when {
            cleaned.startsWith("<b>", i) || cleaned.startsWith("<strong>", i) -> {

                val tag = if (cleaned.startsWith("<b>", i)) "<b>" else "<strong>"
                val endTag = if (tag == "<b>") "</b>" else "</strong>"

                val start = i + tag.length
                val end = cleaned.indexOf(endTag, start)

                if (end != -1) {
                    val text = cleaned.substring(start, end).decodeHtml()

                    builder.withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(text)
                    }

                    isStartOfLine = false
                    i = end + endTag.length
                } else {
                    builder.append(cleaned[i])
                    i++
                }
            }

            cleaned[i] == '<' -> {
                val close = cleaned.indexOf('>', i)
                i = if (close != -1) close + 1 else i + 1
            }

            else -> {
                val char = cleaned[i]
                builder.append(char)

                isStartOfLine = char == '\n'

                i++
            }
        }
    }

    return builder.toAnnotatedString()
}

fun formatDuration(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60

    return when {
        hours > 0 -> "$hours h ${minutes} min"
        else -> "$minutes min"
    }
}