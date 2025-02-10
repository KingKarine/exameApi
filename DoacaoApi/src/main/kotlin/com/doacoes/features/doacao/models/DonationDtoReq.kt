package com.doacoes.features.doacao.models

data class DonationDtoReq(
    val title: String,
    val quantity: Int,
    val address: String,
    val contato: String,
    val expirationDate: String,
    val userId: Int,
)