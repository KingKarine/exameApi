package com.karine.eventosapp.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karine.eventosapp.database.dao.UserDao
import com.karine.eventosapp.database.models.User

@Database([User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    name = "doacoes_db"
                ).build().also {
                    instance = it
                }
            }
        }
    }

}