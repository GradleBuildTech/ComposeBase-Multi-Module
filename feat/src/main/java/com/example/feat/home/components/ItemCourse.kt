package com.example.feat.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.lib.constants.Constants
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.CourseEntity
import com.example.feat.R
import java.util.Locale

@Composable
fun ItemCourse(
    course: CourseEntity,
    onClick: (CourseEntity) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val itemCourseHeight = Constants.COURSE_ITEM_HEIGHT

    Box(
        modifier = Modifier
            .width((screenWidth * 0.4).dp)
            .height(itemCourseHeight.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.Gray)
            .clickable { onClick(course) }
    ) {
        Box(
            modifier = Modifier
                .width((screenWidth * 0.4).dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = course.imageUrl ?: "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight()
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomStart),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = course.name ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = DesignSystem.TITLE_SMALL_STYLE.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            Text(
                text = course.description ?: "",
                maxLines = 3,
                style = DesignSystem.SUBTITLE_SMALL_STYLE.copy(
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 5.dp))
                .background(colorResource(id = R.color.primaryColor))
                .padding(5.dp)
        ){
            Text(
                text = course.level?.lowercase(Locale.ROOT) ?: "Unknown",
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