package com.example.auth.data.domainImpl.usecase

import com.example.auth.data.api.datasource.AuthDataSource
import com.example.auth.domain.usecase.SignInUseCase
import com.example.core.utils.IODispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SignInUseCaseImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    @IODispatcher private val ioDispatcher: CoroutineContext
): SignInUseCase {
}