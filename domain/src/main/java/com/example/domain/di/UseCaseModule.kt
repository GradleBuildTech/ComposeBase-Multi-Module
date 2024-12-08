package com.example.domain.di

import com.example.data.dataSource.auth.AuthDataSource
import com.example.data.dataSource.course.CourseDataSource
import com.example.data.dataSource.eBook.EBookDataSource
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.domain.usecase.auth.SignInUseCase
import com.example.domain.usecase.auth.SignInUseCaseImpl
import com.example.domain.usecase.home.HomeUseCase
import com.example.domain.usecase.home.HomeUseCaseImpl
import com.example.domain.usecase.tutor.ListTutorUseCase
import com.example.domain.usecase.tutor.ListTutorUseCaseImpl
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
    @Provides
    @Singleton
    fun provideHomeUseCase(
        tutorDataSource: TutorDataSource,
        courseDataSource: CourseDataSource,
        eBookDataSource: EBookDataSource,
        dispatcher: CoroutineContext,
    ) : HomeUseCase {
        return HomeUseCaseImpl(
            tutorDataSource = tutorDataSource,
            courseDataSource = courseDataSource,
            eBookDataSource = eBookDataSource,
            ioDispatcher = dispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideListTutorUseCase(
        tutorDataSource: TutorDataSource,
        dispatcher: CoroutineContext,
    ) : ListTutorUseCase {
        return ListTutorUseCaseImpl(
            tutorDataSource = tutorDataSource,
            ioDispatcher = dispatcher,
        )
    }
}