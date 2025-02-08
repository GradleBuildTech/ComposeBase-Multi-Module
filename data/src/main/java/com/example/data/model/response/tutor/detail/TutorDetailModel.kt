package com.example.data.model.response.tutor.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TutorDetailModel(
    var video: String? = null,
    var bio: String? = null,
    var education: String? = null,
    var experience: String? = null,
    var profession: String? = null,
    var accent: String? = null,
    var targetStudent: String? = null,
    var interests: String? = null,
    var languages: String? = null,
    var specialties: String? = null,
    var rating: Double? = null,
    var avgRating: Double? = null,
    var totalFeedback: Int? = null,
    var isNative: Boolean? = null,
    var isFavorite: Boolean? = null,
    var youtubeVideoId: String? = null,
    @Json(name = "User") var user: TutorUserDetailModel? = null,
)