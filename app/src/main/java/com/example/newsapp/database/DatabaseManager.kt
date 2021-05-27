package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.models.User
import com.example.newsapp.models.UserDao


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DatabaseManager: RoomDatabase() {
    abstract fun userDao(): UserDao?
    companion object {
        @Volatile
        private var INSTANCE: DatabaseManager? = null
        @Synchronized
        fun getInstance(context: Context): DatabaseManager {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, DatabaseManager::class.java, "database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}