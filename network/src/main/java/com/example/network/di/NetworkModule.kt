package com.example.network.di

import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton


///✨===============================================
///[NetworkModule] provides the [Retrofit] instance.
///This is used to provide the [Retrofit] instance in the [NetworkModule].
///✨===============================================

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    ///[provideRetrofit] provides the [Retrofit] instance.
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    ///[providesHttpClient] provides the [OkHttpClient] instance.
    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun providesHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(Duration.ofSeconds(30))
            .readTimeout(Duration.ofSeconds(30))
            .build()
    }

    ///[providesLoggingInterceptor] provides the [HttpLoggingInterceptor] instance.
    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}