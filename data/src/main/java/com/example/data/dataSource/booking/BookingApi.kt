package com.example.data.dataSource.booking

import com.example.data.model.response.upcoming.UpComingResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookingApi {
    @GET("${ApiPath.BOOKING}/next")
    suspend fun getUpComing(@Query("dateTime") time : Long): Response<UpComingResponse>
}