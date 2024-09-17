package com.example.auth.data.domainImpl.usecase

import com.example.auth.data.api.datasource.AuthDataSource
import com.example.auth.data.domainImpl.mapper.toDomain
import com.example.auth.domain.model.Token
import com.example.auth.domain.usecase.SignInUseCase
import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.utils.IODispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SignInUseCaseImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    @IODispatcher private val ioDispatcher: CoroutineContext
): SignInUseCase {
    override fun signIn(username: String, password: String): Flow<Either<ExceptionState, Token?>> = flow {
        val response = authDataSource.signIn(username, password)
        val dataConvert = response.mapAndConverterToStateData { it.tokens?.toDomain() }
        emit(dataConvert)
    }
}