package com.example.guard.di

import com.example.guard.auth.AuthGuardController
import com.example.local.dataStore.TokenLocalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GuardModule {

    @Provides
    @Singleton
    fun provideAuthGuard(tokenLocalService: TokenLocalService): AuthGuardController {
        return AuthGuardController(tokenLocalService)
    }
}