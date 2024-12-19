package com.example.domain.di

import com.example.data.dataSource.auth.AuthDataSource
import com.example.data.dataSource.booking.BookingDataSource
import com.example.data.dataSource.course.CourseDataSource
import com.example.data.dataSource.eBook.EBookDataSource
import com.example.data.dataSource.tutor.TutorDataSource
import com.example.data.dataSource.user.UserDataSource
import com.example.domain.usecase.auth.SignInUseCase
import com.example.domain.usecase.auth.SignInUseCaseImpl
import com.example.domain.usecase.course_detail.CourseDetailUseCase
import com.example.domain.usecase.course_detail.CourseDetailUseCaseImpl
import com.example.domain.usecase.document.DocumentUseCase
import com.example.domain.usecase.document.DocumentUseCaseImpl
import com.example.domain.usecase.home.HomeUseCase
import com.example.domain.usecase.home.HomeUseCaseImpl
import com.example.domain.usecase.search.SearchUseCase
import com.example.domain.usecase.search.SearchUseCaseImpl
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
    fun provideBookingUseCase(
        bookingDataSource: BookingDataSource,
        dispatcher: CoroutineContext,
        userDataSource: UserDataSource,
        tutorDataSource: TutorDataSource,
    ): DocumentUseCase {
        return DocumentUseCaseImpl(
            bookingDataSource = bookingDataSource,
            userDataSource = userDataSource,
            tutorDataSource = tutorDataSource,
            ioDispatcher = dispatcher
        )
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(
        courseDataSource: CourseDataSource,
        dispatcher: CoroutineContext,
    ): SearchUseCase {
        return SearchUseCaseImpl(
            courseDataSource = courseDataSource,
            ioDispatcher = dispatcher
        )
    }

    @Provides
    @Singleton
    fun provideCourseDetailUseCase(
        courseDataSource: CourseDataSource,
        dispatcher: CoroutineContext,
    ): CourseDetailUseCase {
        return CourseDetailUseCaseImpl(
            courseDataSource = courseDataSource,
            ioDispatcher = dispatcher
        )
    }
}