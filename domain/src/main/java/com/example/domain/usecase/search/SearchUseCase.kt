package com.example.domain.usecase.search

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.domain.entity.ContentCategoryEntity
import com.example.domain.entity.course.CourseEntity
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    fun fetchCourses(
        paginationRequest: PaginationRequest,
        searchQuery: String? = null,
        contentCategoryId: String? = null,
    ): Flow<Either<ExceptionState, List<CourseEntity>>>
    fun fetchContentCategories(
    ): Flow<Either<ExceptionState, List<ContentCategoryEntity>>>
}