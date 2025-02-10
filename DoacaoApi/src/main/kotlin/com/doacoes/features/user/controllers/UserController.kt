package com.doacoes.features.user.controllers

import at.favre.lib.crypto.bcrypt.BCrypt
import com.doacoes.features.user.entities.UserEntity
import com.doacoes.features.user.entities.UserTable
import com.doacoes.features.user.models.*
import com.doacoes.utils.query

class UserController {
    suspend fun login(request: UserLoginDtoReq): UserDtoRes = query {
        UserEntity.find { UserTable.email eq request.email }.toList().singleOrNull()
            ?.let {
                if (BCrypt.verifyer().verify(request.password.toCharArray(), it.password).verified) {
                    UserDtoRes(it.id.value, it.username, it.email)
                } else throw Exception("Credenciais inválidas")
            } ?: throw Exception("Credenciais inválidas")
    }

    suspend fun createUser(request: UserDtoReq): UserDtoRes = query {
        val user = UserEntity.find { UserTable.email eq request.email }.singleOrNull()
        if (user != null) {
            throw Exception("Email já cadastrado")
        }
        val inserted = UserEntity.new {
            username = request.username
            email = request.email
            password = BCrypt.withDefaults().hashToString(12, request.password.toCharArray())
        }
        inserted.response()
    }
}