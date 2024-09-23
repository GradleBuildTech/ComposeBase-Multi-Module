package com.example.network.extensions

import android.util.Log
import com.example.core.models.GenericException
import com.example.core.models.error.ErrorCode
import com.example.core.models.response.DataResponse
import retrofit2.Response

/**
 * Handle the response from the API
 */
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

/**
 * Handle the API call
 */
inline fun <reified T> handleCall(call: () -> Response<T>): DataResponse<T> {
    return try {
        call.invoke().handReturnDatResponse()
    } catch (e: Exception) {
        Log.d("handleCall", e.message.toString())
        DataResponse.Error(
            errorCode = ErrorCode.DEFAULT,
            message = e.message
        )
    }
}

inline fun <reified T> Response<T>.handReturnDatResponse(): DataResponse<T> {
    return try {
        DataResponse.Success(handleResponse())
    } catch (e: Exception) {
        DataResponse.Error(
            errorCode = ErrorCode.DEFAULT,
            message = e.message
        )
    }
}

