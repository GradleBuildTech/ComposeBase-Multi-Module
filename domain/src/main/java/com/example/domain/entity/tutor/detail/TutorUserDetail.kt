package com.example.domain.entity.tutor.detail

import com.example.domain.entity.course.CoursePreview

data class TutorUserDetail(
    val id: String,
    val level: String,
    val avatar: String,
    val name: String,
    val country: String,
    val language: String,
    val isPublicRecord: Boolean,
    val caredByStaffId: String,
    val studentGroupId: String,
    val zaloUserId: String,
    val courses: List<CoursePreview>

)