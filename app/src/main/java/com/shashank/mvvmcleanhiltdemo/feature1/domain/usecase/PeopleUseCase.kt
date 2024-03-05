package com.shashank.mvvmcleanhiltdemo.feature1.domain.usecase

import android.content.Context
import com.shashank.mvvmcleanhiltdemo.feature1.domain.repository.PeopleRepository
import javax.inject.Inject

class PeopleUseCase @Inject constructor(private val repository: PeopleRepository) {
    fun getPeople() = repository.getPeople()

    fun getAllArticles(filter: String, forceRefresh: Boolean) = repository.getAllArticles(filter,forceRefresh)
}