package com.example.data.model.response.course

import com.example.data.model.PaginationData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoursesResponse(
    @Json(name = "data") val courses: PaginationData<CourseModel>
)