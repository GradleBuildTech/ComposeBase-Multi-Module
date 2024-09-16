package com.example.auth.data.domainImpl.di

import com.example.auth.data.api.datasource.AuthDataSource
import com.example.auth.data.domainImpl.usecase.SignInUseCaseImpl
import com.example.auth.domain.usecase.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {
    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun providesSignInUseCase(dataSource: AuthDataSource, dispatcher: CoroutineContext) : SignInUseCase {
        return SignInUseCaseImpl(authDataSource = dataSource, ioDispatcher = dispatcher)
    }
}