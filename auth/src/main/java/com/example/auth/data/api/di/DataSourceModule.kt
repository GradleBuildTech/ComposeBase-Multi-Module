package com.example.auth.data.api.di

import com.example.auth.data.api.AuthApi
import com.example.auth.data.api.datasource.AuthDataSource
import com.example.auth.data.api.datasource.AuthDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun providesAuthDataSource(authApi: AuthApi): AuthDataSource {
        return AuthDataSourceImpl(authApi)
    }
}