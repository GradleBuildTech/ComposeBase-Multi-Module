package com.example.core.models.stateData

import com.example.core.models.error.ErrorCode


data class ExceptionState(val errorCode: ErrorCode, val errorMessage: String) {
    companion object {
        fun unknownError() = ExceptionState(
            errorCode = ErrorCode.DEFAULT,
            errorMessage = "Unknown error occurred. Please try again later."
        )

        fun notMatchTypeValue() = ExceptionState(
            errorCode = ErrorCode.DEFAULT,
            errorMessage = "Not match type value."
        )
    }
}