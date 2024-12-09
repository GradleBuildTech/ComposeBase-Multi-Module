package com.example.data.model.response.tutor

import com.example.data.model.PaginationData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TutorsResponse(
    val tutors: PaginationData<TutorModel>,
    val favoriteTutor: List<TutorFavoriteModel>
)