package com.example.data.model.response.course

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentCategoryResponse(
    var count: Int,
    var rows: List<ContentCategoryModel>
)