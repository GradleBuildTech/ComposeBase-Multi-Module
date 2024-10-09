package com.example.data.dataSource.course

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.response.course.CoursesResponse

interface CourseDataSource {
    suspend fun fetchCourses(
        paginationRequest: PaginationRequest,
    ): DataResponse<CoursesResponse>
}