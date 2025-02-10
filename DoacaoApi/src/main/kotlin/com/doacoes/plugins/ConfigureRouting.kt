package com.doacoes.plugins

import com.doacoes.features.doacao.routes.questionRoute
import com.doacoes.features.user.routes.userRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRoute() {

    routing {
        userRoute()
        questionRoute()
    }
}
