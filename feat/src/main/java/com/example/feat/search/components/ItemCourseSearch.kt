package com.example.feat.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.course.CourseEntity
import com.example.domain.mapper.toExperienceText
import com.example.feat.R

@Composable
fun ItemCourseSearch(
    course: CourseEntity,
    modifier: Modifier,
    onClicked: (CourseEntity) -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onClicked(course)
            }
    ) {
        Row {
            AsyncImage(
                model = course.imageUrl ?: "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .width(130.dp)
                    .height(130.dp)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Text(
                    text = course.name ?: "",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = DesignSystem.TITLE_SMALL_STYLE.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
                Text(
                    text = course.description ?: "",
                    maxLines = 4,
                    style = DesignSystem.SUBTITLE_SMALL_STYLE
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Levels ",
                        style = DesignSystem.TITLE_SMALL_STYLE.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.primaryColor)
                        )
                    )
                    Text(
                        text = "${course.level} . ${course.topics.size} topics",
                        style = DesignSystem.SUBTITLE_MEDIUM_STYLE
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 5.dp))
                .background(colorResource(id = R.color.primaryColor))
                .padding(5.dp)
        ){
            Text(
                text = course.level?.toExperienceText() ?: "Unknown",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.Center)
            )
        }
    }
}