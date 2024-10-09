package com.example.feat.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.core.components.Skeleton
import com.example.core.lib.constants.Constants

@Composable
fun ItemTutorSkeleton(
) {
    val configuration = LocalConfiguration.current
    val itemTutorHeight = Constants.TUTOR_ITEM_HEIGHT
    val screenWidth = configuration.screenWidthDp

    Skeleton(
        width = screenWidth * 0.55,
        height = itemTutorHeight
    )
}