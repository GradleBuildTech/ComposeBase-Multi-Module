package com.example.auth.domain.model.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInRequest(
    val userName: String,
    val password: String
)