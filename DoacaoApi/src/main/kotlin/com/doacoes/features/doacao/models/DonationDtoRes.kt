package com.doacoes.features.doacao.models

data class DonationDtoRes(
    val id: Int,
    val title: String,
    val quantity: Int,
    val address: String,
    val expirationDate: String,
    val contato: String,
)