package com.example.core.models.response.mapper

import com.example.core.models.error.ErrorCode
import com.example.core.models.response.DataResponse
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState

fun <T> DataResponse<T>.mapToStateData(): Either<ExceptionState, T> {
    return when (this) {
        is DataResponse.Success -> Either.Right(data)
        is DataResponse.Error -> Either.Left(
            ExceptionState(
                errorCode = errorCode ?: ErrorCode.DEFAULT,
                errorMessage = message ?: "Unknown error occurred. Please try again later."
            )
        )
    }
}

fun <T, R> DataResponse<T>.mapAndConverterToStateData(converter: (T) -> R): Either<ExceptionState, R> {
    return when (this) {
        is DataResponse.Success -> Either.Right(converter(data))
        is DataResponse.Error -> Either.Left(
            ExceptionState(
                errorCode = errorCode ?: ErrorCode.DEFAULT,
                errorMessage = message ?: "Unknown error occurred. Please try again later."
            )
        )
    }
}