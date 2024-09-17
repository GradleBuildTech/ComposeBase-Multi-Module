package com.example.auth.data.api.model.response.token

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    val access: ChildToModel?,
    val refresh: ChildToModel?
)