package com.example.auth.data.api.datasource

import com.example.auth.data.api.AuthApi
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource  {

}