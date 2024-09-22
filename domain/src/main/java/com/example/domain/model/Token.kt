package com.example.domain.model

import java.util.Date

data class Token(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val accessTokenExpires: Date? = null,
    val refreshTokenExpires: Date? = null
)