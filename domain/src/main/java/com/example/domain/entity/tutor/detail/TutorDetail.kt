package com.example.domain.entity.tutor.detail

data class TutorDetail(
    val video: String,
    val bio: String,
    val education: String,
    val experience: String,
    val profession: String,
    val accent: String,
    val targetStudent: String,
    val interests: String,
    val languages: String,
    val specialties: String,
    val rating: Double,
    val avgRating: Double,
    val totalFeedback: Int,
    val isNative: Boolean,
    val isFavorite: Boolean,
    val youtubeVideoId: String,
    val user: TutorUserDetail? = null

)