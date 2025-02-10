package com.karine.eventosapp.api.model

data class DonationRequest(
    val title: String = "",
    val quantity: Int = 0,
    val address: String = "",
    val contato: String = "",
    val expirationDate: String = "",
    val userId: Int = 0,
)
