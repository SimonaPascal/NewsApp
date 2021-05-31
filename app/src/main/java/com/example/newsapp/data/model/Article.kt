package com.example.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "article")
class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0


}