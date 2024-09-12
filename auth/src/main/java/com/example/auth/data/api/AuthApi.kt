package com.example.auth.data.api

import com.example.auth.data.api.model.SignInResponse
import com.example.network.utils.ApiPath
import retrofit2.Response
import retrofit2.http.POST

interface AuthApi {
    @POST("${ApiPath.auth}/sign-in")
    suspend fun signIn(): Response<SignInResponse>
}