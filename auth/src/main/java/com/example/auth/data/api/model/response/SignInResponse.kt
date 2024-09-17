package com.example.auth.data.api.model.response

import android.media.session.MediaSession.Token
import com.example.auth.data.api.model.response.token.TokenResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//
@JsonClass(generateAdapter = true)
data class SignInResponse(
    @Json(name = "tokens") val tokens: TokenResponse?
)
