package com.example.newsapp.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsapp.data.model.Article

@Dao
interface ArticleDao {
    @Transaction
    @Query("SELECT * FROM article")
    fun getArticles(): List<Article>
    @Query("DELETE FROM article")
    fun deleteArticles()
    @Insert
    fun insert(article: Article): Long
}