package com.karine.eventosapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.karine.eventosapp.database.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun addUser(user: User)


    @Query("DELETE FROM users")
    suspend fun logout()

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getLoggedInUser(): User?

    @Query("SELECT * FROM users LIMIT 1")
    fun getLoggedInUserFlow(): Flow<User?>

}