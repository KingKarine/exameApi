package com.doacoes.plugins

import com.doacoes.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.mapToMessage
import java.util.*

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<Throwable> { call, error ->
            when (error) {
                is ConstraintViolationException -> {
                    val errorMessage =
                        error.constraintViolations.mapToMessage(baseName = "messages", locale = Locale.ENGLISH)
                            .map { "${it.property}: ${it.message}" }
                    call.respond(
                        HttpStatusCode.BadRequest, ApiResponse.failure(
                            errorMessage, HttpStatusCode.BadRequest
                        )
                    )
                }

                is MissingRequestParameterException -> {
                    call.respond(
                        HttpStatusCode.BadRequest, ApiResponse.failure(
                            "${error.message}", HttpStatusCode.BadRequest
                        )
                    )
                }


                is NullPointerException -> {
                    call.respond(
                        ApiResponse.failure(
                            "Null pointer error : ${error.message}", HttpStatusCode.BadRequest
                        )
                    )
                }


                is TypeCastException -> {
                    call.respond(
                        ApiResponse.failure("Type cast exception", HttpStatusCode.BadRequest)
                    )
                }

                is Exception -> {
                    call.respond(
                        HttpStatusCode.BadRequest, ApiResponse.failure(error.message, HttpStatusCode.NotFound)
                    )
                }

                else -> {
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.failure(
                            "Internal server error : ${error.message}", HttpStatusCode.InternalServerError
                        )
                    )
                }
            }
        }
        status(HttpStatusCode.Unauthorized) { call, statusCode ->
            call.respond(HttpStatusCode.Unauthorized, ApiResponse.failure("Unauthorized api call", statusCode))
        }
    }
}