package com.example.data.model.response.course

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentCategoryModel(
    var id: String,
    var title: String,
    var description: String? = null,
    var key: String,
    var displayOrder: Int? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
)