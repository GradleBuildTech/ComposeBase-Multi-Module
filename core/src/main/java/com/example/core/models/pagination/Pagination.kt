package com.example.core.models.pagination

data class Pagination<T>(
    val data: List<T>,
    val status: Int? = null,
    val totalPages: Int? = null,
    val currentPage: Int? = null,
    val pageSize: Int? = null,
    val errors: String? = null
)