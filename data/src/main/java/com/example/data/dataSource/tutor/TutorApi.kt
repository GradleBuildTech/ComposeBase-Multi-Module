package com.example.data.dataSource.tutor

import com.example.data.model.response.tutor.SearchTutorResponse
import com.example.data.model.response.tutor.TutorsResponse
import com.example.data.model.response.tutor.tutorDetail.TutorDetailModel
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TutorApi {
    @GET("${ApiPath.TUTOR}/more")
    suspend fun fetchTutors(
        @Query("page") page: Int,
        @Query("perPage") pageSize: Int
    ): Response<TutorsResponse>

    @GET("${ApiPath.TUTOR}/search")
    suspend fun  searchTutors(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("search") search: String,
        @Query("filters") filter: Map<String, Any>
    ): Response<SearchTutorResponse>

    @GET("${ApiPath.TUTOR}/{id}")
    suspend fun getTutorById(@Path("id") id: String): Response<TutorDetailModel>

    @POST(ApiPath.MANAGE_FAVORITE_TUTOR)
    suspend fun addTutorToFavorite(@Body request: Map<String, Any>): Response<Unit>
}