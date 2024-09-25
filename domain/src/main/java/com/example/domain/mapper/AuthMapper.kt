package com.example.domain.mapper

import com.example.data.model.response.token.TokenResponse
import com.example.domain.model.Token
import java.time.Instant
import java.util.Date

fun TokenResponse.toDomain(): Token {
    return Token(
        refreshToken = this.refresh?.token,
        accessToken = this.access?.token,
        refreshTokenExpires = Date.from(Instant.parse(this.refresh?.expires)),
        accessTokenExpires = Date.from(Instant.parse(this.access?.expires))
    )
}