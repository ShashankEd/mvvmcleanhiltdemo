package com.shashank.mvvmcleanhiltdemo.feature1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.mvvmcleanhiltdemo.feature1.core.common.Resource
import com.shashank.mvvmcleanhiltdemo.feature1.domain.usecase.PeopleUseCase
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.state.AllArticlesViewModelState
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.state.PeopleViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val peopleUseCase: PeopleUseCase): ViewModel() {
    private val _peopleState = MutableStateFlow(PeopleViewModelState())
    val peopleState: StateFlow<PeopleViewModelState>
        get() = _peopleState

    private val _articlesState = MutableStateFlow(AllArticlesViewModelState())
    val articlesState: StateFlow<AllArticlesViewModelState>
        get() = _articlesState


    private fun getPeople() {
        peopleUseCase.getPeople().onEach {
            when(it) {
                is Resource.Error -> {
                    _peopleState.value = PeopleViewModelState().copy(errorMessage = it.status)
                }
                is Resource.Loading -> {
                    _peopleState.value = PeopleViewModelState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _peopleState.value = PeopleViewModelState().copy(people = it.model)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllArticles(filter: String) {
        peopleUseCase.getAllArticles(filter).onEach {
            when(it) {
                is Resource.Error -> {
                    _articlesState.value = AllArticlesViewModelState().copy(errorMessage = it.status)
                }
                is Resource.Loading -> {
                    _articlesState.value = AllArticlesViewModelState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _articlesState.value = AllArticlesViewModelState().copy(articles = it.model)
                }
            }
        }.launchIn(viewModelScope)

    }
}