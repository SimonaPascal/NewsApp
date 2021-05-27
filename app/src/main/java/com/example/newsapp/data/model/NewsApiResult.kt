package com.example.newsapp.data.model

class NewsApiResult(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

