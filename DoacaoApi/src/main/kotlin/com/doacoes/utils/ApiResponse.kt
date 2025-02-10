package com.doacoes.utils

import io.github.smiley4.ktorswaggerui.dsl.routes.OpenApiRoute
import io.ktor.http.*

data class Response<T>(
    val isSuccess: Boolean,
    val statusCode: Int,
    val data: T,
)


object ApiResponse {
    fun <T> success(data: T, statsCode: HttpStatusCode) = Response(true, data = data, statusCode = statsCode.value)
    fun <T> failure(error: T, statsCode: HttpStatusCode) = Response(false, data = error, statusCode = statsCode.value)
}

inline fun <reified T> OpenApiRoute.apiResponse() {
    return response {
        HttpStatusCode.OK to {
            description = "Successful"
            body<Response<T>> {
                mediaTypes = setOf(ContentType.Application.Json)
                description = "Successful"
            }
        }
        HttpStatusCode.InternalServerError
    }
}
