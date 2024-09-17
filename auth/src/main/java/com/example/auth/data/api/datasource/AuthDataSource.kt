package com.example.auth.data.api.datasource

import com.example.auth.data.api.model.response.SignInResponse
import com.example.core.models.response.DataResponse

interface AuthDataSource {
    suspend fun signIn(
        username: String, password: String
    ): DataResponse<SignInResponse>
}