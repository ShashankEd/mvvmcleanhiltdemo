package com.shashank.mvvmcleanhiltdemo.feature1.data.service

import com.shashank.jetpackmvvmcleanretrofit.feature1.data.dto.AllArticlesDto
import com.shashank.mvvmcleanhiltdemo.feature1.data.dto.PeopleDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleAPI {
    @GET("employees")
    suspend fun getPeople(): PeopleDto

    @GET("everything")
    suspend fun getAllArticles(@Query("q") q: String, @Query("apiKey") apiKey: String): AllArticlesDto

}