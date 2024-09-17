package com.example.auth.data.api.model.response.token

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class ChildToModel(
    val token: String?,
    val expires: Date?
)