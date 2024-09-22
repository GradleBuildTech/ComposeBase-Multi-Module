package com.example.domain.model
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val id: String,
    val email: String,
    val username: String,
    val roles: List<String>,
)