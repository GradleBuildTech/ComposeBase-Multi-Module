package com.example.data.di

import com.example.data.dataSource.user.UserApi
import com.example.data.dataSource.user.UserDataSource
import com.example.data.dataSource.user.UserDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {
    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDataSource(userApi: UserApi): UserDataSource {
        return UserDataSourceImpl(userApi)
    }
}