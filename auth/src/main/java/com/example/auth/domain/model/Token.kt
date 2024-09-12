package com.example.auth.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Token(
    val accessToken: String,
    val refreshToken: String? = null,
)