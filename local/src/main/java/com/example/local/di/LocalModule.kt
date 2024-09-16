package com.example.local.di

import android.content.Context
import android.content.SharedPreferences
import com.example.local.dataStore.TokenLocalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

///[LocalModule] provides the [TokenLocalService] and [SharedPreferences] for the local module.
///This is used to provide the [TokenLocalService] and [SharedPreferences] in the [LocalModule].
///✨===============================================
@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun providesSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            TokenLocalService.TOKEN_PREF,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providesTokenLocalService(sharedPreferences: SharedPreferences): TokenLocalService {
        return TokenLocalService(sharedPreferences)
    }
}