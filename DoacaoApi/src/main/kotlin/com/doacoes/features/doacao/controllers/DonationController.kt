package com.doacoes.features.doacao.controllers

import com.doacoes.features.doacao.entities.DonationEntity
import com.doacoes.features.doacao.entities.DonationTable
import com.doacoes.features.doacao.models.DonationDtoReq
import com.doacoes.features.doacao.models.DonationDtoRes
import com.doacoes.utils.query

class DonationController {

    suspend fun getDonations(): List<DonationDtoRes> = query {
        DonationEntity.all().map { it.response() }
    }

    suspend fun getDonationById(donationId: Int): DonationDtoRes = query {
        DonationEntity.findById(donationId)!!.response()
    }

    suspend fun getUserDonations(userId: Int): List<DonationDtoRes> = query {
        DonationEntity.find { DonationTable.userId eq userId }.map { it.response() }
    }

    suspend fun createDonation(request: DonationDtoReq): DonationDtoRes  = query {
        val createdQuestion = DonationEntity.new {
            title = request.title
            quantity = request.quantity
            address = request.address
            expirationDate = request.expirationDate
            contacto = request.contato
            userId = request.userId

        }

        createdQuestion.response()
    }

    suspend fun updateDonation(donationId: Int, request: DonationDtoReq): DonationDtoRes = query {
        DonationEntity.find { DonationTable.id eq donationId }.singleOrNull()!!.let {
            it.title = request.title
            it.quantity = request.quantity
            it.address = request.address
            it.expirationDate = request.expirationDate
            it.contacto = request.contato
            it.response()
        }

    }

    suspend fun deleteDonation(eventId: Int) = query {
        DonationEntity.find { DonationTable.id eq eventId }
            .singleOrNull()?.delete()
    }

}