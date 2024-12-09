package com.example.data.model.response.tutor

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TutorFavoriteModel(
    var id: String,
    var firstId: String,
    var secondId: String,
    var createdAt: String,
    var updatedAt: String
)