package com.example.feat.home.controller

import com.example.core.models.pagination.PaginationRequest

sealed class HomeEvent {
    data class LoadTutors(val paginationRequest: PaginationRequest) : HomeEvent()
    data class LoadRecommendedCourses(val paginationRequest: PaginationRequest) : HomeEvent()
    data class LoadEBooks(val paginationRequest: PaginationRequest) : HomeEvent()
}