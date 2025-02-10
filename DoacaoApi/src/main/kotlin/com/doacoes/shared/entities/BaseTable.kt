package com.doacoes.shared.entities

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.ZoneOffset

abstract class BaseIntTable(name: String) : IntIdTable(name) {
    val createdAt = datetime("created_at").clientDefault { currentUtc() }
    val updatedAt = datetime("updated_at").nullable()
}

abstract class BaseIntEntity(id: EntityID<Int>, table: BaseIntTable) : Entity<Int>(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}
abstract class BaseIntEntityClass<E : BaseIntEntity>(table: BaseIntTable) : EntityClass<Int, E>(table){
    init {
        EntityHook.subscribe { action ->
            if (action.changeType == EntityChangeType.Updated) {
                try {
                    action.toEntity(this)?.updatedAt = currentUtc()
                } catch (e: Exception) {
                    //nothing much to do here
                }
            }
        }
    }
}

fun currentUtc(): LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)

