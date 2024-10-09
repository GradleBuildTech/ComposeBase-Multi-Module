package com.example.data.di

import com.example.data.dataSource.eBook.EBookApi
import com.example.data.dataSource.eBook.EBookDataSource
import com.example.data.dataSource.eBook.EBookDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EBookModule {
    @Singleton
    @Provides
    fun provideEBookApi(retrofit: Retrofit): EBookApi {
        return retrofit.create(EBookApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEBookDataSource(eBookApi: EBookApi): EBookDataSource {
        return EBookDataSourceImpl(eBookApi)
    }
}