package com.example.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationData<T>(
    val count: Int,
    val rows: List<T>
)