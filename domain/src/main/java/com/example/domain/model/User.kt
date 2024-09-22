package com.example.domain.model

data class User(
    val id: String,
    val email: String,
    val username: String,
    val roles: List<String>,
)