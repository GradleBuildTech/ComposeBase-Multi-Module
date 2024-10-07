package com.example.data.dataSource.tutor

import com.example.data.model.response.tutor.TutorsResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TutorApi {
    @GET("${ApiPath.TUTOR}/more")
    suspend fun fetchTutors(
        @Query("page") page: Int,
        @Query("perPage") pageSize: Int
    ): Response<TutorsResponse>
}