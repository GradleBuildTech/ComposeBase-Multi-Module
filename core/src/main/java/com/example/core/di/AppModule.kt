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

///✨===============================================
///[AppModule] provides the [NetworkConnectivity] implementation.
///This is used to provide the [NetworkConnectivity] in the [NetworkConnectivityImpl] qualifier.
///✨===============================================

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    ///[provideNetworkConnectivity] provides the [NetworkConnectivity] implementation.
    ///This is used to provide the [NetworkConnectivity] in the [NetworkConnectivityImpl] qualifier.
    /// Example: NetworkConnectivityImpl(context)
    /// Special Auth => Implement in NetworkConnectivityImpl
    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity = NetworkConnectivityImpl(context)
}