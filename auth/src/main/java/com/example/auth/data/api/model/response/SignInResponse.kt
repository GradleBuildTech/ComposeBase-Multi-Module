package com.example.auth.data.api.model.response

import com.example.auth.data.api.model.response.token.TokenResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//
@JsonClass(generateAdapter = true)
data class SignInResponse(
    @Json(name = "tokens") var token: TokenResponse? = null
//    val token: String?
)
