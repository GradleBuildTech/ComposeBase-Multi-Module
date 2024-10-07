package com.example.data.di

import com.example.data.dataSource.course.CourseApi
import com.example.data.dataSource.course.CourseDataSource
import com.example.data.dataSource.course.CourseDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CourseModule {
    @Singleton
    @Provides
    fun provideCourseApi(retrofit: Retrofit): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCourseDataSource(courseApi: CourseApi): CourseDataSource {
        return CourseDataSourceImpl(courseApi)
    }
}