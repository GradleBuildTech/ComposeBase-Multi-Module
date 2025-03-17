package com.example.room.di

import android.content.Context
import androidx.room.Room
import com.example.room.AppCourseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppCourseDatabase(
        @ApplicationContext context: Context
    ): AppCourseDatabase = Room.databaseBuilder(
        context = context,
        AppCourseDatabase::class.java,
        "course_database"
    ).build()
}