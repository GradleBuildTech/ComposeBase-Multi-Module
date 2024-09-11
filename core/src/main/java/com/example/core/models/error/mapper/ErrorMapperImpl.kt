package com.example.core.models.error.mapper

import android.content.Context
import com.example.core.models.error.ErrorCode
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


///✨===============================================
///[ErrorMapperImpl] provides the error message based on the error code.
///✨===============================================

class ErrorMapperImpl @Inject constructor(@ApplicationContext val context: Context) : ErrorMapper {
    ///[errorsMap] is a map that contains the error code and the corresponding error message.
    private val _errorsMap: Map<ErrorCode, String>
        get() = mapOf(
            Pair(ErrorCode.NO_INTERNET, "No internet connection"),
            Pair(ErrorCode.UNAUTHORIZED, "Unauthorized"),
            Pair(ErrorCode.FORBIDDEN, "Forbidden"),
            Pair(ErrorCode.NOT_FOUND, "Not found"),
            Pair(ErrorCode.PROCESSABLE, "Processable"),
            Pair(ErrorCode.SERVER_ERROR, "Server error"),
            Pair(ErrorCode.ENTITY_LARGE, "Entity too large")
        ).withDefault { "Default error" }

    ///[getErrorString] returns the error message based on the error code.
    override fun getErrorString(errorCode: ErrorCode): String {
        return _errorsMap.getValue(errorCode)
    }

}