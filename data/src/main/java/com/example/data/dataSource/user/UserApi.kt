package com.example.data.dataSource.user

import com.example.data.model.response.totalTime.TotalTimeResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("${ApiPath.CALL}/total")
    suspend fun getTotalTime(): Response<TotalTimeResponse>
}