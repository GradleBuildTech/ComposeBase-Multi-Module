package com.example.auth.data.domainImpl.mapper

import com.example.auth.data.api.model.response.token.TokenResponse
import com.example.auth.domain.model.Token

/// Mapper for token response
/// @param TokenResponse

fun TokenResponse.toDomain(): Token {
    return Token(
        refreshToken = this.refresh?.token,
        accessToken = this.access?.token,
        refreshTokenExpires = this.refresh?.expires,
        accessTokenExpires = this.access?.expires
    )
}