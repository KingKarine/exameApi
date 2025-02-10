package com.karine.eventosapp.api.model

data class Response<T>(
    val data: T,
    val isSuccess: Boolean,
    val statusCode: Int
)
