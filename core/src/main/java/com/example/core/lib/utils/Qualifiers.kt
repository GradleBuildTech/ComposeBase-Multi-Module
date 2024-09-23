package com.example.core.lib.utils

import javax.inject.Qualifier
///✨===============================================

///[IODispatcher] is a custom qualifier for the I/O dispatcher.
///This is used to differentiate between the I/O dispatcher and the main dispatcher.
///This is used to provide the I/O dispatcher in the [CoroutineModule].

///[Example] usage
///```kotlin
///@Module
///@InstallIn(SingletonComponent::class)
///object CoroutineModule {
///    @Provides
///    @IODispatcher
///    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
///}

///✨===============================================

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class  IODispatcher