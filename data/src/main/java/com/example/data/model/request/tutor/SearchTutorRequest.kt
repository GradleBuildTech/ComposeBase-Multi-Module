package com.example.data.model.request.tutor

data class SearchTutorRequest(
    val page: Int,
    val perPage: Int,
    val search: String,
    val topics: List<String>? = null,
    val nationality: Map<String, Any>? = null
)