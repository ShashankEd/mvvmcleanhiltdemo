package com.shashank.mvvmcleanhiltdemo.feature1.domain.repository

import com.shashank.mvvmcleanhiltdemo.feature1.core.common.Resource
import com.shashank.mvvmcleanhiltdemo.feature1.domain.model.People
import kotlinx.coroutines.flow.Flow

//@Module
//@InstallIn(ViewModelComponent::class)
interface PeopleRepository {
    fun getPeople(): Flow<Resource<People>>
}