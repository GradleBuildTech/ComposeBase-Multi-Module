package com.example.network.token

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenRequest(
    val refreshToken: String,
    //String with default "7"
    val timezone: String = "7"
)