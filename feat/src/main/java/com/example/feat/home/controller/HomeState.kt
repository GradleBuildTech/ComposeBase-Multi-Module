package com.example.feat.home.controller

import com.example.domain.entity.CourseEntity
import com.example.domain.entity.EBookEntity
import com.example.domain.entity.TutorEntity


data class HomeState(
    val isLoading: Boolean = false,
    val isLoadingTutors: Boolean = false,
    val tutors: List<TutorEntity> = emptyList(),
    val isLoadingRecommendedCourses: Boolean = false,
    val recommendedCourses: List<CourseEntity> = emptyList(),
    val isLoadingEBooks: Boolean = false,
    val eBooks: List<EBookEntity> = emptyList()
)