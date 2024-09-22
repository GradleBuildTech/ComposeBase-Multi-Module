package com.example.data.dataSource.auth

import com.example.core.models.response.DataResponse
import com.example.data.model.request.auth.SignInRequest
import com.example.data.model.response.auth.SignInResponse

interface AuthDataSource {
    suspend fun signIn(
        request: SignInRequest
    ): DataResponse<SignInResponse>
}