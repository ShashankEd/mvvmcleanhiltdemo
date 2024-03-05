package com.shashank.mvvmcleanhiltdemo.feature1.data.repository

import com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model.AllArticles
import com.shashank.mvvmcleanhiltdemo.feature1.core.common.Resource
import com.shashank.mvvmcleanhiltdemo.feature1.core.utils.Constants
import com.shashank.mvvmcleanhiltdemo.feature1.data.api.PeopleAPI
import com.shashank.mvvmcleanhiltdemo.feature1.data.mapper.toDomainAllArticles
import com.shashank.mvvmcleanhiltdemo.feature1.data.mapper.toDomainPeople
import com.shashank.mvvmcleanhiltdemo.feature1.domain.model.People
import com.shashank.mvvmcleanhiltdemo.feature1.domain.repository.PeopleRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class PeopleRepositoryImpl @Inject constructor(private val peopleAPI: PeopleAPI): PeopleRepository {
    override fun getPeople(): Flow<Resource<People>> = flow {
        emit(Resource.Loading())
        val result = peopleAPI.getPeople().toDomainPeople()
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

    override fun getAllArticles(filter: String): Flow<Resource<AllArticles>> = flow {
        emit(Resource.Loading())
        val result = peopleAPI.getAllArticles(filter, Constants.API_KEY).toDomainAllArticles()
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO).catch {
        emit(Resource.Error(it.message.toString()))
    }
}