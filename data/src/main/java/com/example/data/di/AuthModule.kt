package com.example.data.di

import com.example.data.dataSource.auth.AuthApi
import com.example.data.dataSource.auth.AuthDataSource
import com.example.data.dataSource.auth.AuthDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthModule {
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