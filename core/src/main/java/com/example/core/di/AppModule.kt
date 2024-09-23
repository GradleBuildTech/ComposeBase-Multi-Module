package com.example.core.di

import android.content.Context
import com.example.core.lib.utils.network.NetworkConnectivity
import com.example.core.lib.utils.network.NetworkConnectivityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity = NetworkConnectivityImpl(context)
}