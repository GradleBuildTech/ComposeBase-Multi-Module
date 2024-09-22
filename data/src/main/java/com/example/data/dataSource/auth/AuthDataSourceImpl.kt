package com.example.data.dataSource.auth

import com.example.core.models.response.DataResponse
import com.example.data.model.request.auth.SignInRequest
import com.example.data.model.response.auth.SignInResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource {

    override suspend fun signIn(
        request: SignInRequest
    ): DataResponse<SignInResponse> {
        return handleCall { authApi.signIn(request) }
    }
}