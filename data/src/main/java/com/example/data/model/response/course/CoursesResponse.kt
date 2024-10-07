package com.example.data.model.response.course

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoursesResponse(
    @Json(name = "data") val courses: CoursesData
)

@JsonClass(generateAdapter = true)
data class CoursesData(
    val count: Int,
    val rows: List<CourseModel>
)