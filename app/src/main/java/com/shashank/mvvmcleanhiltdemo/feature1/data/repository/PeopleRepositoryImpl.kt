package com.shashank.mvvmcleanhiltdemo.feature1.data.repository

import com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model.AllArticles
import com.shashank.mvvmcleanhiltdemo.feature1.core.common.Resource
import com.shashank.mvvmcleanhiltdemo.feature1.core.utils.Constants
import com.shashank.mvvmcleanhiltdemo.feature1.data.api.PeopleAPI
import com.shashank.mvvmcleanhiltdemo.feature1.data.datasource.NewsDataSource
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
class PeopleRepositoryImpl @Inject constructor(
    private val peopleAPI: PeopleAPI,
    private val dataSource: NewsDataSource
): PeopleRepository {
    override fun getPeople(): Flow<Resource<People>> = flow {
        emit(Resource.Loading())
        val result = peopleAPI.getPeople().toDomainPeople()
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

    override fun getAllArticles(filter: String, forceRefresh: Boolean): Flow<Resource<AllArticles>> = flow {
        //here we need to check from the DB first then we'll call API
        val articlesFromDB = dataSource.getArticlesFromDb()
        println("Got Articles ${articlesFromDB.size} from DB!!")
        emit(Resource.Loading())
        if(forceRefresh) {
            println("forceRefresh Clearing Articles ${articlesFromDB.size} from DB and calling API!!")
            dataSource.clearArticles()
            val result = peopleAPI.getAllArticles(filter, Constants.API_KEY).toDomainAllArticles()
            dataSource.insertArticles(result.articles)
            emit(Resource.Success(result))
        } else {
            println("Check DB is empty!!")
            if(articlesFromDB.isEmpty()) {
                println("Check DB is empty, call API, populate table!!")
                val result = peopleAPI.getAllArticles(filter, Constants.API_KEY).toDomainAllArticles()
                dataSource.insertArticles(result.articles)
                emit(Resource.Success(result))
            } else {
                println("Check DB isn't empty, fetching from the table!!")
                //form the AllArticle object and pass it
                val result = AllArticles(
                    articles = articlesFromDB,
                    status = "success",
                    totalResults = articlesFromDB.size
                )
                emit(Resource.Success(result))
            }
        }
    }.flowOn(Dispatchers.IO).catch {
        emit(Resource.Error(it.message.toString()))
    }
}