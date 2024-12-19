package com.example.data.dataSource.course

import com.example.data.model.response.course.ContentCategoryResponse
import com.example.data.model.response.course.CourseDetailResponse
import com.example.data.model.response.course.CoursesResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CourseApi {
    @GET(ApiPath.COURSE)
    suspend fun fetchCourses(
        @Query("page") page: Int,
        @Query("perPage") pageSize: Int,
        @Query("q") searchQuery: String? = null,
        @Query("categoryId") contentCategoryId: String? = null,
    ): Response<CoursesResponse>

    @GET(ApiPath.CONTENT_CATEGORY)
    suspend fun fetchContentCategories(): Response<ContentCategoryResponse>

    @GET("${ApiPath.COURSE}/{courseId}")
    suspend fun fetchCourseDetail(
        @Path("courseId") courseId: String
    ): Response<CourseDetailResponse>

}