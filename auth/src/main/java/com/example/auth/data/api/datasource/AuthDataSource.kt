package com.example.auth.data.api.datasource

import com.example.auth.data.api.model.response.SignInResponse
import com.example.auth.domain.model.request.SignInRequest
import com.example.core.models.response.DataResponse
import retrofit2.http.Body

interface AuthDataSource {
    suspend fun signIn(
        request: SignInRequest
    ): DataResponse<SignInResponse>
}