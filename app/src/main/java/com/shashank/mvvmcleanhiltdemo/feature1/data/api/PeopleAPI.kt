package com.shashank.mvvmcleanhiltdemo.feature1.data.api

import com.shashank.mvvmcleanhiltdemo.feature1.data.dto.PeopleDto
import retrofit2.http.GET

interface PeopleAPI {
    @GET("employees")
    suspend fun getPeople(): PeopleDto
}