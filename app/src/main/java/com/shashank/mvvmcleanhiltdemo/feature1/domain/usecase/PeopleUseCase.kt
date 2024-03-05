package com.shashank.mvvmcleanhiltdemo.feature1.domain.usecase

import com.shashank.mvvmcleanhiltdemo.feature1.domain.repository.PeopleRepository
import javax.inject.Inject

class PeopleUseCase @Inject constructor(private val repository: PeopleRepository) {
    fun getPeople() = repository.getPeople()

    fun getAllArticles(filter: String) = repository.getAllArticles(filter)
}