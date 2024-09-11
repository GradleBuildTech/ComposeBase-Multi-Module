package com.example.core.models.error.mapper

import com.example.core.models.error.ErrorCode

interface ErrorMapper {
    fun getErrorString(errorCode: ErrorCode): String
}