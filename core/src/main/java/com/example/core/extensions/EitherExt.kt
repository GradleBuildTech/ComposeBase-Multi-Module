package com.example.core.extensions

import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState

/// Extension function to convert Either<ExceptionState, Any> to Either<ExceptionState, List<Any>>
fun Either<ExceptionState, Any>.toListEither(): Either<ExceptionState, List<Any>> = when(this){
    is Either.Left -> this
    is Either.Right -> {
        when(val value = this.rightValue()) {
            is List<*> -> Either.Right(value.filterIsInstance<Any>())
            else -> Either.Left(ExceptionState.notMatchTypeValue())
        }
    }
}