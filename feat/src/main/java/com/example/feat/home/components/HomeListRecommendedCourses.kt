package com.example.feat.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.CourseEntity
import com.example.feat.home.controller.HomeViewModel


@Composable
fun HomeListRecommendedCourses(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickListRecommendedCourses: () -> Unit,
    onClickItemRecommendedCourse: (CourseEntity) -> Unit,
){
    val uiState by homeViewModel.uiState.collectAsState()
    val recommendedCourses = uiState.recommendedCourses
    val isLoadingRecommendedCourses = uiState.isLoadingRecommendedCourses

    HeadingTitle(title = "\uD83D\uDCBB Recommend courses") {
        onClickListRecommendedCourses()
    }
    Spacer(modifier = Modifier.height(10.dp) )
    Box(modifier = Modifier.fillMaxWidth()){
        if (isLoadingRecommendedCourses) {
            HorizontalList(size = 5) {
                ItemCourseSkeleton()
            }
        } else {
            HorizontalList(size = recommendedCourses.size) { index ->
                ItemCourse(course = recommendedCourses[index]) {
                    onClickItemRecommendedCourse(it)
                }
            }
        }
    }
}

