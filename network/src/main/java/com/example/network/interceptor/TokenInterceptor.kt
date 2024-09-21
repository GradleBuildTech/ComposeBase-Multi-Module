package com.example.network.interceptor

import com.example.local.dataStore.TokenLocalService
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class TokenInterceptor @Inject constructor(
    private  val tokenLocalService: TokenLocalService
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = tokenLocalService.getAccessToken()
        if(token != null) {
            val request = original.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            return chain.proceed(request)
        }
        return chain.proceed(original)
    }
}
