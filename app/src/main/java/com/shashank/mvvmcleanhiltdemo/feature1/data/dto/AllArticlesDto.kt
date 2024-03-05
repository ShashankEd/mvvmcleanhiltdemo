package com.shashank.jetpackmvvmcleanretrofit.feature1.data.dto

data class AllArticlesDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)