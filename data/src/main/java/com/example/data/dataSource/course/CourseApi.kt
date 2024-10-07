package com.example.data.dataSource.course

import com.example.data.model.response.course.CoursesResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApi {
    @GET(ApiPath.COURSE)
    suspend fun fetchCourses(
        @Query("page") page: Int,
        @Query("perPage") pageSize: Int
    ): Response<CoursesResponse>
}