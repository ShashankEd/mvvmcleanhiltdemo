package com.shashank.mvvmcleanhiltdemo.feature1.data.mapper

import android.util.Log
import com.shashank.jetpackmvvmcleanretrofit.feature1.data.dto.AllArticlesDto
import com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model.AllArticles
import com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model.Article

fun AllArticlesDto.toDomainAllArticles(): AllArticles =
    AllArticles(articles.map {
        Article(it.author, it.title, it.urlToImage, it.publishedAt)
    }, status, totalResults)
