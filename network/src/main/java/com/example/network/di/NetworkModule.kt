package com.example.network.di

import com.example.core.models.moshi.MyKotlinJsonAdapterFactory
import com.example.core.models.moshi.MyStandardJsonAdapters
import com.example.local.dataStore.SecureTokenLocalService
import com.example.network.BuildConfig
import com.example.network.interceptor.TokenInterceptor
import com.example.network.token.RefreshTokenApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    // 💌 Interceptor to add headers
    private val headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    // 🧠 Moshi - JSON converter with custom adapters
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(MyKotlinJsonAdapterFactory())
        .add(MyStandardJsonAdapters.FACTORY)
        .build()

    // 🔍 Logging interceptor
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // 🚀 Main Retrofit instance for api
    @Singleton
    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .client(okHttpClient)
        .build()

    // 🔄 Retrofit for refreshing tokens
    @Singleton
    @Provides
    @Named("RefreshRetrofit")
    fun provideRefreshRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL) // Nếu khác domain, đổi chỗ này nhaa
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()

    // 🔐 OkHttpClient: with header and token interceptors
    @Singleton
    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(tokenInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .connectTimeout(Duration.ofSeconds(30))
        .readTimeout(Duration.ofSeconds(30))
        .build()

    // 🔁 API for refreshing tokens
    @Singleton
    @Provides
    fun provideRefreshTokenApi(
        @Named("RefreshRetrofit") retrofit: Retrofit
    ): RefreshTokenApi = retrofit.create(RefreshTokenApi::class.java)

    // 🛡️ TokenInterceptor với DI
    @Singleton
    @Provides
    fun provideTokenInterceptor(
        secureTokenLocalService: SecureTokenLocalService,
        moshi: Moshi,
        @Named("RefreshRetrofit") refreshRetrofit: Retrofit
    ): TokenInterceptor = TokenInterceptor(secureTokenLocalService, moshi, refreshRetrofit)
}
