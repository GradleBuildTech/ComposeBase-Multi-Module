package com.example.feat.course_detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.Skeleton
import com.example.core.extensions.toEEEEDDMMYYYY
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.TopicEntity
import com.example.domain.mapper.toExperienceText
import com.example.feat.R
import com.example.feat.course_detail.components.CourseDetailToolbar
import com.example.feat.course_detail.controller.CourseDetailUiEvent
import com.example.feat.course_detail.controller.CourseDetailViewModel

@Composable
fun CourseDetailScreen(
    courseId: String,
    courseDetailViewModel: CourseDetailViewModel = hiltViewModel()
) {
    val lazyScrollState = rememberLazyListState()
    val uiState = courseDetailViewModel.uiState.collectAsState()
    LaunchedEffect(courseId) {
        courseDetailViewModel.onEvent(CourseDetailUiEvent.FetchCourseDetail(courseId))
    }
    val scaffoldBackgroundColor = MaterialTheme.colorScheme.background

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(scaffoldBackgroundColor),
        topBar = {
            if (uiState.value.courseDetail != null) {
                CourseDetailToolbar(
                    lazyScrollState = lazyScrollState,
                    title = uiState.value.courseDetail?.name ?: "",
                    backgroundImageUrl = uiState.value.courseDetail?.imageUrl ?: "",
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 0.dp
                    )
                    .animateContentSize(),
                state = lazyScrollState
            ) {
                when (uiState.value.courseDetail) {
                    null -> {
                        item { CourseDetailLoading() }
                    }

                    else -> {
                        item {
                            Spacer(modifier = Modifier.height(45.dp))
                            Text(
                                text = uiState.value.courseDetail?.description ?: "",
                                style = DesignSystem.SUBTITLE_MEDIUM_STYLE
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Overview",
                                style = DesignSystem.TITLE_LARGE_STYLE
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            OverviewField(
                                title = "Why take this course?",
                                content = uiState.value.courseDetail?.reason ?: "",
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            OverviewField(
                                title = "What will you be able to do?",
                                content = uiState.value.courseDetail?.purpose ?: "",
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Experience level",
                                style = DesignSystem.TITLE_LARGE_STYLE
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = uiState.value.courseDetail?.level?.toExperienceText() ?: "",
                                style = DesignSystem.SUBTITLE_MEDIUM_STYLE.copy(
                                    color = colorResource(id = R.color.hintColor)
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            BoxTime(
                                createdTime = uiState.value.courseDetail?.createdAt ?: "",
                                updatedTime = uiState.value.courseDetail?.updatedAt ?: ""
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "All levels",
                                style = DesignSystem.TITLE_LARGE_STYLE
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            ListLevels(topics = uiState.value.courseDetail?.topics ?: emptyList())
                        }
                    }
                }
            }
    }
}

@Composable
private fun OverviewField(
    title: String,
    content: String
) {
    Column {
        Text(
            text = title,
            style = DesignSystem.SUBTITLE_LARGE_STYLE.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                Icons.Default.Info,
                contentDescription = "Info",
                tint = colorResource(id = R.color.primaryColor)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = content,
                style = DesignSystem.SUBTITLE_MEDIUM_STYLE.copy(
                    color = colorResource(id = R.color.hintColor)
                )
            )
        }
    }
}

@Composable
private fun ListLevels(
    topics: List<TopicEntity>
) {
    topics.forEach() { topic ->
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.white))
                .padding(10.dp),
        ) {
            Text(
                text = "${topic.orderCourse}. ${topic.name}",
                style = DesignSystem.SUBTITLE_LARGE_STYLE.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
private fun CourseDetailLoading() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    Skeleton(width = screenWidth.toDouble(), height = 16.0)
    Spacer(modifier = Modifier.height(10.dp))
    Skeleton(width = screenWidth.toDouble(), height = 16.0)
    Spacer(modifier = Modifier.height(20.dp))
    Skeleton(width = screenWidth.toDouble(), height = 16.0)
    Spacer(modifier = Modifier.height(5.dp))
    Skeleton(width = screenWidth.toDouble(), height = 16.0)
    Spacer(modifier = Modifier.height(5.dp))
    Skeleton(width = screenWidth.toDouble(), height = 100.0)
    Spacer(modifier = Modifier.height(20.dp))
    Skeleton(width = screenWidth.toDouble(), height = 16.0)
    Spacer(modifier = Modifier.height(5.dp))
    Skeleton(width = screenWidth.toDouble(), height = 100.0)
}

@Composable
private fun BoxTime(
    createdTime: String,
    updatedTime: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.primaryColor))
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Created At", style = DesignSystem.SUBTITLE_LARGE_STYLE.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )
                Text(
                    text = createdTime.toEEEEDDMMYYYY(),
                    style = DesignSystem.SUBTITLE_MEDIUM_STYLE.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
            }
            Column {
                Text(
                    text = "Updated At", style = DesignSystem.SUBTITLE_LARGE_STYLE.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )
                Text(
                    text = updatedTime.toEEEEDDMMYYYY(),
                    style = DesignSystem.SUBTITLE_MEDIUM_STYLE.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
            }
        }
    }
}