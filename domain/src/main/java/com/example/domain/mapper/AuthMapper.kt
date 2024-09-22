package com.example.domain.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.model.response.token.TokenResponse
import com.example.domain.model.Token
import java.time.Instant
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
fun TokenResponse.toDomain(): Token {
    return Token(
        refreshToken = this.refresh?.token,
        accessToken = this.access?.token,
        refreshTokenExpires = Date.from(Instant.parse(this.refresh?.expires)),
        accessTokenExpires = Date.from(Instant.parse(this.access?.expires))
    )
}