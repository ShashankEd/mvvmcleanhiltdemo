package com.shashank.mvvmcleanhiltdemo.feature1.presentation.state

import com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model.AllArticles

data class AllArticlesViewModelState(
    val articles: AllArticles? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean = false
)
