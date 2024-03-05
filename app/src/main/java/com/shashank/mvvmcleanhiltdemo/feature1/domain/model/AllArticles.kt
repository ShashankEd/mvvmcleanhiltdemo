package com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model


data class AllArticles(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
