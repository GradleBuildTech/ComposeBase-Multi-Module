package com.example.network.token

import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenApi {
    @POST("${ApiPath.AUTH}/refresh-token")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Response<RefreshTokenResponse>
}