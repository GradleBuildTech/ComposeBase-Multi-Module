package com.example.network.interceptor

import com.example.local.dataStore.TokenLocalService
import com.example.network.BuildConfig
import com.example.network.token.RefreshTokenApi
import com.example.network.token.RefreshTokenRequest
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/// This class is responsible for intercepting the request and adding the token to the header
/// If the response code is 401 or 403, it will refresh the token and add the new token to the header
/// This class is used to intercept the request and add the token to the header
/// If the response code is 401 or 403, it will refresh the token and add the new token to the header
/// @param tokenLocalService is used to get the token from the local storage
/// @param retrofit is used to create the RefreshTokenApi instance
/// @constructor Create empty Token interceptor

class TokenInterceptor(
    private val tokenLocalService: TokenLocalService,
    private val moshi: Moshi
//    private val retrofit: Retrofit
) : Interceptor {

    /// Refresh token call
    /// This function is used to refresh the token
    /// It will call the refresh token api and get the new token
    /// If the response is successful, it will set the new token and refresh token in the local storage
    /// @return new token

    private suspend fun refreshTokenCall(): String? {
        val refreshToken = tokenLocalService.getRefreshToken() ?: return null
        if (refreshToken.isEmpty()) return null
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .baseUrl(BuildConfig.API_URL)
            .build()
        val refreshTokenApi = retrofitBuilder.create(RefreshTokenApi::class.java)
        val response = refreshTokenApi.refreshToken(
            RefreshTokenRequest(
                refreshToken = refreshToken
            )
        )
        if (response.isSuccessful) {
            val newToken = response.body()?.accessToken ?: ""
            val newRefreshToken = response.body()?.refreshToken ?: ""
            tokenLocalService.setToken(
                accessToken = newToken,
                refreshToken = newRefreshToken
            )
            return newToken
        }
        return refreshToken
    }

    /// Not clean implement of intercept function
    /// This function is not clean because it has a lot of responsibilities
    /// It should be refactored to have only one responsibility

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = tokenLocalService.getAccessToken() ?: return chain.proceed(original)
        val request = original.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        val initialResponse = chain.proceed(request)
        if (initialResponse.code == 401 || initialResponse.code == 403) {
            synchronized(this) {
                val newToken = runBlocking { refreshTokenCall() }
                if (newToken?.isEmpty() == true) return initialResponse
                val newRequest = original.newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()
                return chain.proceed(newRequest)
            }
        }
        return initialResponse
    }

}
