package com.example.network.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core.models.moshi.MyKotlinJsonAdapterFactory
import com.example.core.models.moshi.MyStandardJsonAdapters
import com.example.core.utils.Constants
import com.example.network.interceptor.TokenInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import javax.inject.Singleton


///✨===============================================
///[NetworkModule] provides the [Retrofit] instance.
///This is used to provide the [Retrofit] instance in the [NetworkModule].
///✨===============================================

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    ///[headerInterceptor] is an [Interceptor] that adds the header to the request.
    ///This is used to add the header to the request.
    /// Example: Content-Type: application/json,
    /// Special Auth => Implement in TokenInterceptor
    private val _headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    ///[provideRetrofit] provides the [Retrofit] instance.
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, providesHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .baseUrl(Constants.BASE_URL)
            .client(providesHttpClient)
            .build()
    }

    ///[providesHttpClient] provides the [OkHttpClient] instance.
    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun providesHttpClient(
        tokenInterceptor: TokenInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(_headerInterceptor)
            .addInterceptor(tokenInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(Duration.ofSeconds(30))
            .readTimeout(Duration.ofSeconds(30))
            .build()
    }

    ///[moshi] provides the [Moshi] instance.
    ///This is used to provide the [Moshi] instance. => Code converter
    @Singleton
    @Provides
    fun moshi(): Moshi {
        return Moshi.Builder()
            .add(MyKotlinJsonAdapterFactory())
            .add(MyStandardJsonAdapters.FACTORY)
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