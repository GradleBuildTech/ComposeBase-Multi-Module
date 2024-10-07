package com.example.feat.home.controller

sealed class HomeEvent {
    data class LoadTutors(val page: Int, val pageSize: Int) : HomeEvent()
    data class LoadRecommendedCourses(val page: Int, val pageSize: Int) : HomeEvent()
    data class LoadEBooks(val page: Int, val pageSize: Int) : HomeEvent()
}