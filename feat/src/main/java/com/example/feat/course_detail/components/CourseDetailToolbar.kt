package com.example.feat.course_detail.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.AsyncImage
import com.example.feat.R
import com.example.core.lib.constants.DesignSystem

@OptIn(ExperimentalMotionApi::class)
@Composable
fun CourseDetailToolbar(
    lazyScrollState: LazyListState,
    backgroundImageUrl: String? = null,
    title: String? = null
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    val motionScene = remember {
        context.resources.openRawResource(R.raw.collapse_toolbar).readBytes().decodeToString()
    }

    val isExpanded by remember {
        derivedStateOf { lazyScrollState.firstVisibleItemScrollOffset == 0 }
    }

    val animationSpec = 500

    val progress by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 1f,
        tween(animationSpec), label = "progress"
    )

    val motionHeight by animateDpAsState(
        targetValue = if (isExpanded) (screenHeight * 0.3).dp else 56.dp,
        tween(animationSpec), label = "motionHeight"
    )

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.primaryColor))
            .height(motionHeight)
    ) {
        if (backgroundImageUrl != null && isExpanded) {
            AsyncImage(
                model = backgroundImageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .layoutId("backgroundImage")
            )
        } else {
            Box(
                modifier = Modifier
                    .layoutId("backgroundImage")
                    .background(color = colorResource(id = R.color.primaryColor))
            )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.layoutId("backButton")
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Button",
                tint = Color.White
            )
        }

        Text(
            text = title ?: "",
            modifier = Modifier.layoutId("title"),
            style = if (!isExpanded) DesignSystem.TITLE_SMALL_STYLE.copy(
                color = Color.White,
                fontSize = 20.sp
            ) else
                DesignSystem.TITLE_LARGE_STYLE.copy(
                    color = colorResource(id = R.color.primaryColor),
                    fontSize = 25.sp
                ),
        )
    }
}

