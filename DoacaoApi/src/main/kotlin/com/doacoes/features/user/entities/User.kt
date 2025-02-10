package com.doacoes.features.user.entities

import com.doacoes.features.user.models.UserDtoRes
import com.doacoes.shared.entities.BaseIntEntity
import com.doacoes.shared.entities.BaseIntEntityClass
import com.doacoes.shared.entities.BaseIntTable
import org.jetbrains.exposed.dao.id.EntityID

object UserTable : BaseIntTable("users") {
    val username = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 200)
}

class UserEntity(id: EntityID<Int>) : BaseIntEntity(id, UserTable) {
    companion object : BaseIntEntityClass<UserEntity>(UserTable)

    var username by UserTable.username
    var email by UserTable.email
    var password by UserTable.password

    fun response() = UserDtoRes(
        id.value,
        username,
        email
    )
}


