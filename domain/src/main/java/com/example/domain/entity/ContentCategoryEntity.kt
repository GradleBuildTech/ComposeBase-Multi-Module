package com.example.domain.entity

data class ContentCategoryEntity(
    var id: String,
    var title: String,
    var description: String? = null,
    var key: String,
    var displayOrder: Int? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + key.hashCode()
        result = 31 * result + (displayOrder ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }
}