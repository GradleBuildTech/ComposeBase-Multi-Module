package com.example.feat.document.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.feat.R

@Composable
fun TimeRichText(
    header: String,
    hours: Long,
    minutes: Long,
    seconds: Long? = null,
) {
    Box(
        contentAlignment = Alignment.Center // Center the content within the Box
    ) {
        val contents = buildList<String> {
            add(header)
            add(" $hours ")
            add("hours, ")
            add("$minutes ")
            add("minutes ")
            if (seconds != null) {
                add("and ")
                add("$seconds ")
                add("seconds")
            }
        }

        val textSpans = contents.mapIndexed { index, element ->
            val fontWeight = if (index in intArrayOf(0, 2, 4, 5, 7)) FontWeight.Normal else FontWeight.Bold
            AnnotatedString(
                text = element,
                spanStyle = SpanStyle(
                    fontSize = 16.sp,
                    fontWeight = fontWeight,
                    letterSpacing = 0.15.sp,
                    color = colorResource(id = R.color.white)
                )
            )
        }

        Text(
            text = textSpans.reduce { acc, annotatedString -> acc + annotatedString },
            textAlign = TextAlign.Center, // Center the text content
        )
    }
}