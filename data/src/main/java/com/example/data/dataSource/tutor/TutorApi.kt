package com.example.data.dataSource.tutor

import com.example.data.model.response.tutor.AddFavoriteTutorResponse
import com.example.data.model.response.tutor.TutorDetailResponse
import com.example.data.model.response.tutor.TutorModel
import com.example.data.model.response.tutor.TutorsResponse
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
    @POST("${ApiPath.USER}/manageFavoriteTutor")
    suspend fun addFavoriteTutor(
        @Body body: Map<String, String>
    ): Response<AddFavoriteTutorResponse>

    @GET("${ApiPath.TUTOR}/{id}")
    suspend fun fetchTutorDetail(
        @Path("id") id: String
    ): Response<TutorDetailResponse>
}