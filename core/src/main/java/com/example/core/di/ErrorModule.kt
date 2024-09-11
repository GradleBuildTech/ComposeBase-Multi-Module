package com.example.core.di

import com.example.core.models.error.mapper.ErrorMapper
import com.example.core.models.error.mapper.ErrorMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


///✨===============================================
///[ErrorModule] provides the [ErrorMapper] implementation.
///This is used to provide the [ErrorMapper] in the [ErrorMapperImpl] qualifier.
///✨===============================================

@Module
@InstallIn(SingletonComponent::class)
abstract  class ErrorModule {
    @Binds
    @Singleton
    abstract fun bindErrorMapper(errorMapperImpl: ErrorMapperImpl): ErrorMapper
}