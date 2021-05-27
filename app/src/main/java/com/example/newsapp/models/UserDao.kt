package com.example.newsapp.models
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM user where id =:id")
    fun getUser(id: Long): User?
    @Query("SELECT * FROM user where emailAddress=:email AND password=:password")
    fun getUserByEmailAndPassword(email: String, password: String): User?
    @Insert
    fun insert(user: User): Long
}