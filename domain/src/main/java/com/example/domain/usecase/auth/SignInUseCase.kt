package com.example.domain.usecase.auth

import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {
    fun signIn( username: String, password: String) : Flow<Either<ExceptionState, Token?>>
}