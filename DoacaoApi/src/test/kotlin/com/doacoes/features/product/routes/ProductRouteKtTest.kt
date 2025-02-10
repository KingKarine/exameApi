package com.doacoes.features.product.routes

import com.doacoes.plugins.configureRoute
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class ProductRouteKtTest {

    @Test
    fun testGetProductsCategorySubcategory() = testApplication {
        application {
            configureRoute()
        }
        client.get("/products/{category}/{subCategory}").apply {
            TODO("Please write your test here")
        }
    }
}