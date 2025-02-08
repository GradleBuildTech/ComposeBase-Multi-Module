package com.example.data.model.response.course

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoursePreviewModel(
    var courseId: String? = null,
    var name: String? = null,
)