package com.example.data.dataSource.course

import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.response.DataResponse
import com.example.data.model.response.course.ContentCategoryResponse
import com.example.data.model.response.course.CoursesResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class CourseDataSourceImpl @Inject constructor(
    private val courseApi: CourseApi
) : CourseDataSource{
    override suspend fun fetchCourses(
        paginationRequest: PaginationRequest,
        searchQuery: String?,
        contentCategoryId: String?,
    ): DataResponse<CoursesResponse> {
        return handleCall {
            courseApi.fetchCourses(
                page = paginationRequest.page,
                pageSize = paginationRequest.pageSize,
                searchQuery = searchQuery,
                contentCategoryId = contentCategoryId
            )
        }
    }

    override suspend fun fetchContentCategories(): DataResponse<ContentCategoryResponse> {
        return handleCall { courseApi.fetchContentCategories() }
    }
}