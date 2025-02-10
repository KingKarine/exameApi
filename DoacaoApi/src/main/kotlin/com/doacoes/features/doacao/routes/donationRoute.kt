package com.doacoes.features.doacao.routes

import com.doacoes.features.doacao.controllers.DonationController
import com.doacoes.features.doacao.models.DonationDtoReq
import com.doacoes.utils.ApiResponse
import com.doacoes.utils.apiResponse
import io.github.smiley4.ktorswaggerui.dsl.routing.delete
import io.github.smiley4.ktorswaggerui.dsl.routing.get
import io.github.smiley4.ktorswaggerui.dsl.routing.post
import io.github.smiley4.ktorswaggerui.dsl.routing.put
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.questionRoute(
    donationController: DonationController = DonationController(),
) {
    route("donations") {
        get({
            tags("Donation")
            apiResponse<List<DonationDtoReq>>()
        }) {
            call.respond(
                ApiResponse.success(
                    donationController.getDonations(),
                    HttpStatusCode.OK
                )
            )
        }

        get("/user/{userId}",{
            tags("Donation")
            request {
                queryParameter<Int>("userId")
            }
            apiResponse<List<DonationDtoReq>>()
        }) {
            val userId = call.parameters["userId"] ?: return@get
            call.respond(
                ApiResponse.success(
                    donationController.getUserDonations(userId.toInt()),
                    HttpStatusCode.OK
                )
            )
        }

        get("/{id}", {
            tags("Donation")
            request {
                queryParameter<Int>("id")
            }
            apiResponse<List<DonationDtoReq>>()
        }) {
            val id = call.parameters["id"] ?: return@get
            call.respond(
                ApiResponse.success(
                    donationController.getDonationById(id.toInt()),
                    HttpStatusCode.OK
                )
            )
        }


        post({
            tags("Donation")
            request {
                body<DonationDtoReq>()
            }
        }) {
            val requestBody = call.receive<DonationDtoReq>()

            call.respond(
                ApiResponse.success(
                    donationController.createDonation(requestBody),
                    HttpStatusCode.Created
                )
            )
        }

        put("/{id}", {
            tags("Donation")
            request {
                queryParameter<Int>("id")
                body<DonationDtoReq>()
            }
            apiResponse<DonationDtoReq>()
        }) {
            val id = call.parameters["id"] ?: return@put
            val requestBody = call.receive<DonationDtoReq>()

            call.respond(
                ApiResponse.success(
                    donationController.updateDonation(id.toInt(), requestBody),
                    HttpStatusCode.OK
                )
            )
        }

        delete("/{id}", {
            tags("Donation")
            request {
                queryParameter<Int>("id")
            }
        }) {
            val id = call.parameters["id"] ?: return@delete
            call.respond(
                ApiResponse.success(
                    donationController.deleteDonation(id.toInt()),
                    HttpStatusCode.OK
                )
            )
        }

    }
}

