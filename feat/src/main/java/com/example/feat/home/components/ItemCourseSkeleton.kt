package com.example.feat.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.core.components.Skeleton
import com.example.core.lib.constants.Constants

@Composable
fun ItemCourseSkeleton(
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val itemCourseHeight = Constants.COURSE_ITEM_HEIGHT

    Skeleton(
        width = screenWidth * 0.4,
        height = itemCourseHeight
    )
}