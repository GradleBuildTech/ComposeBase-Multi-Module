package com.example.auth.data.api.datasource

import com.example.auth.data.api.AuthApi
import com.example.auth.data.api.model.response.SignInResponse
import com.example.core.models.response.DataResponse
import com.example.network.extensions.handleCall
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource  {
    override suspend fun signIn(
        username: String,
        password: String
    ): DataResponse<SignInResponse> {
        return handleCall {
            authApi.signIn()
        }
    }

}