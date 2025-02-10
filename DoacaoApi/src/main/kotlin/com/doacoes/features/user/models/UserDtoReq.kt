package com.doacoes.features.user.models

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotEmpty
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class UserDtoReq(
    val username: String,
    val email: String,
    val password: String,
){
    fun validation() {
        validate(this) {
            validate(UserDtoReq::username).isNotNull().isNotEmpty()
            validate(UserDtoReq::email).isNotNull().isNotEmpty()
            validate(UserDtoReq::password).isNotNull().isNotEmpty().hasSize(8)
        }
    }
}
