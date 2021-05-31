package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.model.Article
import com.example.newsapp.models.ArticleDao
import com.example.newsapp.models.User
import com.example.newsapp.models.UserDao


@Database(entities = [User::class, Article::class], version = 2, exportSchema = false)
abstract class DatabaseManager: RoomDatabase() {
    abstract fun userDao(): UserDao?
    abstract fun articleDao(): ArticleDao?
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