package com.shashank.mvvmcleanhiltdemo.feature1.presentation.state

import com.shashank.mvvmcleanhiltdemo.feature1.domain.model.People

data class PeopleViewModelState (
    val people: People? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean = false
)