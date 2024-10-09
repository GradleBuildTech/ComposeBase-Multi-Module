package com.example.data.di

import com.example.data.dataSource.tutor.TutorApi
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.data.dataSource.tutor.TutorDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TutorModule {
    @Singleton
    @Provides
    fun provideTutorApi(retrofit: Retrofit): TutorApi {
        return retrofit.create(TutorApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTutorDataSource(tutorApi: TutorApi): TutorDataSource {
        return TutorDataSourceImpl(tutorApi)
    }
}