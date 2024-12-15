package com.example.data.model.response.course

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicModel(
    var id: String,
    var courseId: String,
    var orderCourse: Int,
    var name: String,
    var beforeTheClassNotes: String? = null,
    var afterTheClassNotes: String? = null,
    var nameFile: String,
    var numberOfPages: Int? = null,
    var description: String? = null,
    var videoUrl: String? = null,
    var type: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    )