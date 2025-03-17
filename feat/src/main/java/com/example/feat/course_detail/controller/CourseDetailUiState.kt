package com.example.feat.course_detail.controller

import com.example.domain.entity.course.CourseEntity

data class CourseDetailUiState(
    val isLoading: Boolean = false,
    val courseDetail: CourseEntity? = null,
)