package com.example.data.model.response.course

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDetailResponse(
    val message: String,
    val data: CourseModel
)