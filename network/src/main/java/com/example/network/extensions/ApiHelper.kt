package com.example.network.extensions

import com.example.core.models.GenericException
import retrofit2.Response

fun <T> Response<T>.handleResponse(): T {
    return try {
        this.takeIf { it.isSuccessful }?.body() ?: throw Exception("Error")
    } catch (e: Exception) {
        throw GenericException(
            message = e.message,
            hasUserFriendlyMessage = false
        )
    }
}

suspend fun <T> handleCall(call: suspend () -> Response<T>): T {
    return try {
        call.invoke().handleResponse()
    } catch (e: Exception) {
        throw GenericException(
            message = e.message,
            hasUserFriendlyMessage = false
        )
    }
}