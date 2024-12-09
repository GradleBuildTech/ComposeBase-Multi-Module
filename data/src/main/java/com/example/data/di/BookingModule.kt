package com.example.data.di

import com.example.data.dataSource.booking.BookingApi
import com.example.data.dataSource.booking.BookingDataSource
import com.example.data.dataSource.booking.BookingDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookingModule {
    @Singleton
    @Provides
    fun provideBookingApi(retrofit: Retrofit): BookingApi {
        return retrofit.create(BookingApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBookingDataSource(bookingApi: BookingApi): BookingDataSource {
        return BookingDataSourceImpl(bookingApi)
    }
}