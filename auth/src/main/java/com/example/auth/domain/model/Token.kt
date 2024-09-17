package com.example.auth.domain.model

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class Token(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val accessTokenExpires: Date? = null,
    val refreshTokenExpires: Date? = null
)