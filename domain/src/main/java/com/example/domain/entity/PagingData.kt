package com.example.domain.entity

data class PaginationData<T>(
    val pageIndex: Int,
    val data: List<T>
) {
    companion object {
        fun <T> from(json: Map<String, Any>, data: List<T>): PaginationData<T> {
            val pageIndex = json["page"] as? Int ?: 0
            return PaginationData(data = data, pageIndex = pageIndex)
        }
    }
}