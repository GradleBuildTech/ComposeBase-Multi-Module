package com.example.core.di

import com.example.core.lib.utils.IODispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

///✨===============================================
///[CoroutineModule] provides the I/O dispatcher.
///This is used to provide the I/O dispatcher in the [IODispatcher] qualifier.
///✨===============================================

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}