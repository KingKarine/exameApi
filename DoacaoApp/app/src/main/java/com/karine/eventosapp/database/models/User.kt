package com.karine.eventosapp.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val username: String,
    val email: String,
)