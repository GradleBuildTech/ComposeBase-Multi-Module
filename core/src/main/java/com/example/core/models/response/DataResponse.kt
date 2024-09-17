package com.example.core.models.response

import com.example.core.models.error.ErrorCode

sealed class DataResponse<T> {
    data class Success<T>(val data: T) : DataResponse<T>()
    data class Error<T>(
        val code: Int? = null,
        val errorCode: ErrorCode? = ErrorCode.DEFAULT,
        val message: String? = "Unknown error occurred. Please try again later."
    ) : DataResponse<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> {
                "Success[data=$data]"
            }

            is Error -> {
                "Error[exception=$errorCode]"
            }
        }
    }
}