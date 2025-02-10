package com.doacoes.features.user.routes

import com.doacoes.features.user.controllers.UserController
import com.doacoes.features.user.models.UserDtoReq
import com.doacoes.features.user.models.UserDtoRes
import com.doacoes.features.user.models.UserLoginDtoReq
import com.doacoes.utils.ApiResponse
import com.doacoes.utils.apiResponse
import io.github.smiley4.ktorswaggerui.dsl.routing.post
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoute(
    userController: UserController = UserController(),
) {
    route("user") {
        post("login", {
            tags("User")
            summary = "User login"
            request {
                body<UserDtoRes>()
            }
            apiResponse<UserLoginDtoReq>()
        }) {
            val requestBody = call.receive<UserLoginDtoReq>()
            requestBody.validation()
            call.respond(
                ApiResponse.success(
                    userController.login(requestBody),
                    HttpStatusCode.OK
                )
            )
        }
        post("register", {
            tags("User")
            summary = "Create user"
            request {
                body<UserDtoReq>()
            }
            apiResponse<UserDtoRes>()
        }) {
            val requestBody = call.receive<UserDtoReq>()
            requestBody.validation()
            call.respond(
                ApiResponse.success(
                    userController.createUser(requestBody),
                    HttpStatusCode.OK
                )
            )
        }
    }
}