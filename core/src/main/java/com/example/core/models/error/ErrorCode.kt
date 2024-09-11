package com.example.core.models.error

enum class ErrorCode(value: Int) {
    DEFAULT(0),
    NO_INTERNET(1),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    PROCESSABLE(422),
    SERVER_ERROR(500),
    ENTITY_LARGE(413);

    companion object {
        fun fromValue(value: Int): ErrorCode {
            return when (value) {
                0 -> DEFAULT
                1 -> NO_INTERNET
                401 -> UNAUTHORIZED
                403 -> FORBIDDEN
                404 -> NOT_FOUND
                422 -> PROCESSABLE
                500 -> SERVER_ERROR
                413 -> ENTITY_LARGE
                else -> DEFAULT
            }
        }
    }
}