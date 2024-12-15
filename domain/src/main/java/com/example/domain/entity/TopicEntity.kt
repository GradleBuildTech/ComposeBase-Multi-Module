package com.example.domain.entity

data class TopicEntity(
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