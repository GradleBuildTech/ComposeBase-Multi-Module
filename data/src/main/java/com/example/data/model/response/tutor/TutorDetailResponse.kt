package com.example.data.model.response.tutor

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TutorDetailResponse(
    val message: String,
    val data: TutorModel
)