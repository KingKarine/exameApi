package com.karine.eventosapp.api.model

data class CreateAccountRequest(
    val username: String,
    val email: String,
    val password: String
)
