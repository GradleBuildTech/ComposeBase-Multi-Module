package com.example.domain.di

import com.example.data.dataSource.auth.AuthDataSource
import com.example.domain.usecase.auth.SignInUseCase
import com.example.domain.usecase.auth.SignInUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
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