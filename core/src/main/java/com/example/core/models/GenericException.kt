package com.example.core.models

import java.io.IOException

///[GenericException] is a custom exception class that extends [IOException].
///This is used to handle exceptions in the network layer.
///[message] is the message of the exception.
///[hasUserFriendlyMessage] is a boolean that indicates whether the exception has a user-friendly message.

data class GenericException(
    override val message: String?,
    val hasUserFriendlyMessage: Boolean
) : IOException()