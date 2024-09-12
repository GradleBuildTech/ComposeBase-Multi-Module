package com.example.auth.data.api.model

import android.media.session.MediaSession.Token
import com.squareup.moshi.JsonClass

//
@JsonClass(generateAdapter = true)
data class SignInResponse(val token: Token)
