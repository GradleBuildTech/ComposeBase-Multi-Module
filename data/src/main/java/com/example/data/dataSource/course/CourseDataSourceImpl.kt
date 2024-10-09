package com.example.data.dataSource.course

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.response.course.CoursesResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class CourseDataSourceImpl @Inject constructor(
    private val courseApi: CourseApi
) : CourseDataSource{
    override suspend fun fetchCourses(paginationRequest: PaginationRequest): DataResponse<CoursesResponse> {
        return handleCall { courseApi.fetchCourses(page = paginationRequest.page, pageSize = paginationRequest.pageSize) }
    }
}