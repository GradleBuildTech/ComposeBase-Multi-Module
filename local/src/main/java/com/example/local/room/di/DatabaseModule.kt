package com.example.local.room.di

import android.content.Context
import androidx.room.Room
import com.example.local.room.AppDatabase
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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context = context,
        AppDatabase::class.java,
        "database"
    ).build()

    @Provides
    @Singleton
    fun provideCourseDao(appDatabase: AppDatabase) = appDatabase.courseDao()

    @Provides
    @Singleton
    fun provideTutorDao(appDatabase: AppDatabase) = appDatabase.tutorDao()

    @Provides
    @Singleton
    fun provideEBookDao(appDatabase: AppDatabase) = appDatabase.eBookDao()
}