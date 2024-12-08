package com.example.data.model.response.tutor

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchTutorResponse(
    val count: Int,
    val tutors: List<TutorModel>
)