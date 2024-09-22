package com.example.data.model.request.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInRequest(
    val email: String,
    val password: String
)