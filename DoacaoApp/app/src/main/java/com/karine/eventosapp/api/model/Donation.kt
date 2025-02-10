package com.karine.eventosapp.api.model

data class Donation(
    val id: Int,
    val title: String,
    val quantity: Int,
    val address: String,
    val expirationDate: String,
    val contato: String
)
