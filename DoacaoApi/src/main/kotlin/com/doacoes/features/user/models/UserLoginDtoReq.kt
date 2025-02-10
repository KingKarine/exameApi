package com.doacoes.features.user.models

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotEmpty
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class UserLoginDtoReq(
    val email: String,
    val password: String
) {
    fun validation() {
        validate(this) {
            validate(UserLoginDtoReq::email).isNotNull().isNotEmpty()
            validate(UserLoginDtoReq::password).isNotNull().isNotEmpty().hasSize(8)
        }
    }
}
