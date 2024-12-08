package com.example.data.model.response.course

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoursePreviewModel(
    val courseId: String,
    val name: String
)