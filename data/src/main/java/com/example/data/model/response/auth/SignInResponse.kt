package com.example.data.model.response.auth

import com.example.data.model.response.token.TokenResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInResponse(
    @Json(name = "tokens") var token: TokenResponse? = null
//    val token: String?
)
