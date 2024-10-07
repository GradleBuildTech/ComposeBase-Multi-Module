package com.example.data.dataSource.course

import com.example.core.models.response.DataResponse
import com.example.data.model.response.course.CoursesResponse

interface CourseDataSource {
    suspend fun fetchCourses(
        page: Int, pageSize: Int,
    ): DataResponse<CoursesResponse>
}