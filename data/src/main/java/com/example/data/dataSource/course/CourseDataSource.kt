package com.example.data.dataSource.course

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.response.course.ContentCategoryResponse
import com.example.data.model.response.course.CoursesResponse

interface CourseDataSource {
    suspend fun fetchCourses(
        paginationRequest: PaginationRequest,
        searchQuery: String? = null,
        contentCategoryId: String? = null,
    ): DataResponse<CoursesResponse>
    suspend fun fetchContentCategories(): DataResponse<ContentCategoryResponse>
}