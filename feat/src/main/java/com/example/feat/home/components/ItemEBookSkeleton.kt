package com.example.feat.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.core.components.Skeleton
import com.example.core.lib.constants.Constants

@Composable
fun ItemEBookSkeleton(
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val itemEBookHeight = Constants.EBOOK_ITEM_HEIGHT

    Skeleton(
        width = screenWidth * 0.75,
        height = itemEBookHeight
    )

}