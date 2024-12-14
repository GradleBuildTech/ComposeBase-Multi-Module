package com.example.feat.search.controller

import com.example.domain.entity.ContentCategoryEntity
import com.example.domain.entity.CourseEntity

data class SearchUiState(
    val isLoading: Boolean = false,
    val courses: List<CourseEntity> = emptyList(),
    val contentCategories: List<ContentCategoryEntity> = emptyList(),
    val selectedContentCategory : ContentCategoryEntity? = null,
    val searchText: String = "",
){
    override fun equals(other: Any?): Boolean {
        if (other is SearchUiState) {
            if (courses.size != other.courses.size) return false
            for (i in courses.indices) {
                if (courses[i] != other.courses[i]) return false
            }
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = isLoading.hashCode()
        result = 31 * result + courses.hashCode()
        result = 31 * result + contentCategories.hashCode()
        result = 31 * result + selectedContentCategory.hashCode()
        result = 31 * result + searchText.hashCode()
        return result
    }
}