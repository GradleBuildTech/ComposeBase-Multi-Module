package com.example.data.model.response.token

import android.media.session.MediaSession.Token
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenResponse(val token: Token)