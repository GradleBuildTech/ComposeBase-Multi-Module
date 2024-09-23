package com.example.data.dataSource.auth

import com.example.data.model.request.auth.SignInRequest
import com.example.data.model.response.auth.SignInResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {
    @POST("${ApiPath.AUTH}/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}