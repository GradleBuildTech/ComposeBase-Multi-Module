package com.example.feat.course_detail.controller

sealed class CourseDetailUiEvent {
    data class FetchCourseDetail(val id: String) : CourseDetailUiEvent()
}