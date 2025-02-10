package com.karine.eventosapp.ui.screens.route

import kotlinx.serialization.Serializable


@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

@Serializable
data object HomeRoute

@Serializable
data object AddDonationRoute

@Serializable
data class EditDonationRoute(val donationId: Int)

