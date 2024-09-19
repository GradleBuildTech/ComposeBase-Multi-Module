package com.example.auth.data.api.model.response.token

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    var access: ChildToModel? = null,
    var refresh: ChildToModel? = null
)