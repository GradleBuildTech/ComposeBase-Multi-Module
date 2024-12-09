package com.example.data.model.response.tutor

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddFavoriteTutorResponse(
    val message: String,
    val result: Any
)