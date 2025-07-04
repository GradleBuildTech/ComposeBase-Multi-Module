package com.example.domain.usecase.auth

import com.example.core.models.response.mapper.mapAndConverterToStateData
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.lib.utils.IODispatcher
import com.example.data.dataSource.auth.AuthDataSource
import com.example.data.model.request.auth.SignInRequest
import com.example.domain.mapper.toDomain
import com.example.domain.model.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class SignInUseCaseImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : SignInUseCase {
    override fun signIn(username: String, password: String): Flow<Either<ExceptionState, Token?>> =
        flow {
            val response = authDataSource.signIn(
                SignInRequest(
                    email = username,
                    password = password
                )
            )
            val dataConvert = response.mapAndConverterToStateData { it.token?.toDomain() }
            emit(dataConvert)
        }.flowOn(ioDispatcher)
}