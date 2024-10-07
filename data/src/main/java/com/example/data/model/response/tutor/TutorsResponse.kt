package com.example.data.model.response.tutor

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TutorsResponse(
    val tutors: TutorsData
)

@JsonClass(generateAdapter = true)
data class TutorsData(
    val count: Int,
    val rows: List<TutorModel>
)