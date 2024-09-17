package com.example.auth.domain.usecase

import com.example.auth.domain.model.Token
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {
    fun signIn( username: String, password: String) : Flow<Either<ExceptionState, Token?>>
}